package plugin.view;

import net.miginfocom.swing.MigLayout;
import plugin.metrics.OquareMetrics;
import plugin.metrics.OquareModelMetrics;
import plugin.utils.ParserXML;
import plugin.utils.ScrollPane;
import plugin.view.components.MetricsGraph;
import plugin.view.components.MetricsPanel;
//import um.cmd.RunOquare;
//import um.gwt.shared.oquare.model.Oquare;

import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.event.EventType;
import org.protege.editor.owl.model.event.OWLModelManagerListener;
import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OquarePluginMetrics extends AbstractOWLViewComponent implements Layout {

	Logger log = LoggerFactory.getLogger(OquarePluginMetrics.class);

	private MetricsPanel standardMetricsPanel;
	private MetricsGraph standardMetricsGraph;

	private OquareMetrics oquareMetrics = null;

	private static final String TEXT_UNSATISFIABLE_CLASSES = "The ontology has unsatisfiable classes so we stop the Oquare metrics calculation";
	private static final String TEXT_ERROR_ONTOLOGY = "java.lang.IllegalArgumentException: The file specified in --ontology";

	private static final Integer OK_STATUS = 0;
	private static final Integer ERROR_PROCESS_START = 1;
	private static final Integer ERROR_CALCULATING_METRICS = 2;
	private static final Integer ERROR_UNSATISFIABLE_CLASSES = 3;
	private static final Integer ERROR_UNKNOW = 4;
	private static final String WINDOWS = "win";
	private static final String MAC = "osx";
	private static final String UNIX = "uni";

	private static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean isWindows() {
		return OS.contains("win");
	}

	public static boolean isMac() {
		return OS.contains("mac");
	}

	public static boolean isUnix() {
		return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
	}

	public static String getOS() {
		if (isWindows()) {
			return WINDOWS;
		} else if (isMac()) {
			return MAC;
		} else if (isUnix()) {
			return UNIX;
		} else {
			return "err";
		}
	}

	private OWLModelManager modelManager;
	private final OWLModelManagerListener modelManagerListener = event -> {
		if (event.getType() == EventType.ONTOLOGY_LOADED) {
			initPlugin();
		}
	};

	public OquarePluginMetrics() {
	}

	public OquarePluginMetrics(OWLModelManager modelManager) {
		this.modelManager = modelManager;
	}

	@Override
	protected void initialiseOWLView() {

		modelManager = getOWLModelManager();
		modelManager.addListener(modelManagerListener);

		if (!modelManager.getActiveOntology().isEmpty()) {
			initPlugin();

		} else {
			setLayout(new BorderLayout());
			add(new JLabel("No ontology loaded", SwingConstants.CENTER));
		}

	}

	public void initPlugin() {
		removeAll();
		JLabel jl = new JLabel("Calculating metrics, please wait...", SwingConstants.CENTER);
		add(jl);

		// Here we can return some object of whatever type
		// we specified for the first template parameter.
		SwingWorker<Integer, Void> worker = new SwingWorker<Integer, Void>() {
			@Override
			protected Integer doInBackground() throws Exception {
				ProcessBuilder processBuilder = new ProcessBuilder();

				String path = modelManager.getOntologyPhysicalURI(modelManager.getActiveOntology()).toString();

				// Protege adds "file:/" if it's a local file
				if (!path.contains("http") || !path.contains("https"))
					path = path.substring(6);

				// The oquare jar must be in the plugins folder, otherwise it won't work.
				String currentOS = getOS();
				if (currentOS.equals(WINDOWS)) {
					processBuilder.command("cmd.exe", "/c",
							"java -jar ./plugins/oquare-versions.jar --ontology " + path);
				} else if (currentOS.equals(MAC)) {
					processBuilder.command("/bin/bash", "-c",
							"java -jar ./plugins/oquare-versions.jar --ontology " + path);
				} else if (currentOS.equals(UNIX)) {
					processBuilder.command("/bin/bash", "-c",
							"java -jar ./plugins/oquare-versions.jar --ontology " + path);
				} else {
					return ERROR_PROCESS_START;
				}

				Process process = null;
				try {
					process = processBuilder.start();
				} catch (IOException e) {
					log.error(e.toString());
					return ERROR_PROCESS_START;
				}

				// This thread waits until the metrics are done
				process.waitFor();

				// The error input is the only thing we need. If oquare executes successfully,
				// the path to the xml file is in the error output. The errors are too in the
				// error input
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				StringBuilder builder = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
					builder.append(System.getProperty("line.separator"));
				}
				String resultErr = builder.toString();

				if (resultErr.contains(TEXT_UNSATISFIABLE_CLASSES)) {
					return ERROR_UNSATISFIABLE_CLASSES;
				}
				if (resultErr.contains(TEXT_ERROR_ONTOLOGY)) {
					return ERROR_UNKNOW;
				}

				if (resultErr.contains("Results saved in")) {

					String file = "";
					Pattern pattern = Pattern.compile("Results saved in: \"(.*.xml)\"", Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(resultErr);
					boolean matchFound = matcher.find();
					if (matchFound) {
						file = matcher.group(1);
						oquareMetrics = ParserXML.getOquareMetrics(file);
						// file.delete();
						return OK_STATUS;
					} else {
						return ERROR_CALCULATING_METRICS;
					}

				}
				return ERROR_UNKNOW;
			}

			// Can safely update the GUI from this method.
			protected void done() {

				Integer status;
				try {
					// Retrieve the return value of doInBackground.
					status = get();
					if (status == OK_STATUS) {
						removeAll();
						setLayout(new MigLayout("nogrid, flowy, fill, insets 0 0 0 5"));
						standardMetricsPanel = new MetricsPanel(oquareMetrics);
						standardMetricsGraph = new MetricsGraph(oquareMetrics);
						JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, standardMetricsPanel,
								standardMetricsGraph);

						mainSplitPane.setDividerLocation(600);
						add(mainSplitPane, "grow");
						revalidate();
						return;
					}

					if (status == ERROR_PROCESS_START) {
						setLayout(new BorderLayout());
						jl.setText("Error trying to execute the command for calculating the metrics");
						revalidate();
						return;
					}
					if (status == ERROR_CALCULATING_METRICS) {
						setLayout(new BorderLayout());
						jl.setText("Error trying to calculate the metrics.");
						revalidate();
						return;
					}
					if (status == ERROR_UNSATISFIABLE_CLASSES) {
						setLayout(new BorderLayout());
						jl.setText("The ontology contains unsatisfiable clasess. Cannot create metrics");
						revalidate();
						return;
					}

					if (status == ERROR_UNKNOW) {
						setLayout(new BorderLayout());
						jl.setText(
								"The ontology is not valid. If it is an ontology by URL changing the protocol to 'https' might solve it.");
						revalidate();
						return;
					}

				} catch (InterruptedException e) {
					// This is thrown if the thread's interrupted.
					setLayout(new BorderLayout());
					jl.setText("Something went wrong. Try again.");
					revalidate();
					return;
				} catch (ExecutionException e) {
					// This is thrown if we throw an exception
					// from doInBackground.
					setLayout(new BorderLayout());
					jl.setText("Something went wrong. Try again.");
					revalidate();
					return;
				}

			}

		};

		worker.execute();
		setLayout(new BorderLayout());
		add(jl);
	}

	@Override
	protected void disposeOWLView() {
		modelManager.removeListener(modelManagerListener);

		if (standardMetricsPanel != null) {
			standardMetricsPanel.dispose();
		}
		if (standardMetricsGraph != null) {
			standardMetricsGraph.dispose();
		}

	}

}
