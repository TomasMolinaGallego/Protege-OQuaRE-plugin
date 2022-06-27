package plugin.view.components;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import plugin.metrics.Metric;
import plugin.metrics.OquareModelMetrics;
import plugin.utils.TitleLabel;
import plugin.utils.TooltipsStrings;
import plugin.view.Layout;

//Class specific for the creation of the tab of the metrics of Oquare model.
public class MetricsModelTab extends JPanel implements Layout {

	Logger log = LoggerFactory.getLogger(MetricsModelTab.class);
	private static final DecimalFormat dfZero = new DecimalFormat("0.00");

	private OquareModelMetrics metrics;
	private List<Metric> oquareModelMetrics = new LinkedList<>();
	private List<Metric> oquareModelCompatibilityMetrics = new LinkedList<>();
	private List<Metric> oquareModelFunctionalAdequacyMetrics = new LinkedList<>();
	private List<Metric> oquareModelMaintainabilityMetrics = new LinkedList<>();
	private List<Metric> oquareModelOperabilityMetrics = new LinkedList<>();
	private List<Metric> oquareModelReliabilityMetrics = new LinkedList<>();
	private List<Metric> oquareModelStructuralMetrics = new LinkedList<>();
	private List<Metric> oquareModelTransferabilityMetrics = new LinkedList<>();

	private List<MetricsRow> metricRows = new ArrayList<>();

	private static final String MODEL_OQUARE_VALUE_TITLE = "OquareValue";
	private static final String MODEL_COMPATIBILITY_TITLE = "Compatibility";
	private static final String MODEL_FUNCTIONAL_ADEQUACY_TITLE = "FunctionalAdequacy";
	private static final String MODEL_MAINTAINABILITY_TITLE = "Maintainability";
	private static final String MODEL_OPERABILITY_TITLE = "Operability";
	private static final String MODEL_REALIABILITY_TITLE = "Reliability";
	private static final String MODEL_STRUCTURAL_TITLE = "Structural";
	private static final String MODEL_TRANSFERABILITY_TITLE = "Transferability";

	public MetricsModelTab(String title, OquareModelMetrics metrics) {
		this.metrics = metrics;
		setLayout(new MigLayout("wrap 2, insets 0, gap 1px", "[grow]10[grow]"));
		setBackground(BACKGROUND_COLOR);
//		createTitle(title);
		addMetrics();
		addPanels();
	}

	private void addMetrics() {
		// Get the metrics separated
		createMetrics(metrics.getMapOquareModel(), oquareModelMetrics);
		createMetrics(metrics.getMapOquareModelCompatibility(), oquareModelCompatibilityMetrics);
		createMetrics(metrics.getMapOquareModelFunctionalAdequacy(), oquareModelFunctionalAdequacyMetrics);
		createMetrics(metrics.getMapOquareModelMaintainability(), oquareModelMaintainabilityMetrics);
		createMetrics(metrics.getMapOquareModelOperability(), oquareModelOperabilityMetrics);
		createMetrics(metrics.getMapOquareModelReliability(), oquareModelReliabilityMetrics);
		createMetrics(metrics.getMapOquareModelStructural(), oquareModelStructuralMetrics);
		createMetrics(metrics.getMapOquareModelTransferability(), oquareModelTransferabilityMetrics);

	}

	private void addPanels() {
		// Create rows from metrics
		createTitle(MODEL_OQUARE_VALUE_TITLE, dfZero.format(oquareModelMetrics.get(0).getValue()));
		createMetricsSection(MODEL_COMPATIBILITY_TITLE, oquareModelCompatibilityMetrics);
		createMetricsSection(MODEL_FUNCTIONAL_ADEQUACY_TITLE, oquareModelFunctionalAdequacyMetrics);
		createMetricsSection(MODEL_MAINTAINABILITY_TITLE, oquareModelMaintainabilityMetrics);
		createMetricsSection(MODEL_OPERABILITY_TITLE, oquareModelOperabilityMetrics);
		createMetricsSection(MODEL_REALIABILITY_TITLE, oquareModelReliabilityMetrics);
		createMetricsSection(MODEL_STRUCTURAL_TITLE, oquareModelStructuralMetrics);
		createMetricsSection(MODEL_TRANSFERABILITY_TITLE, oquareModelTransferabilityMetrics);
	}

	private void createMetrics(Map<String, Double> map, List<Metric> listMetrics) {
		for (String clave : map.keySet()) {
			Metric metrica = new Metric();
			metrica.setName(clave);
			metrica.setValue(map.get(clave));
			listMetrics.add(metrica);
		}
	}

	private void createMetricsSection(String title, List<Metric> metrics) {

		Metric met = metrics.stream().filter(m -> m.getName().equals(title)).findFirst().get();
		createTitle(title, dfZero.format(met.getValue()));
		metrics.remove(met);

		for (Metric metric : metrics) {
			MetricsRow standardMetricRow = new MetricsRow(metric);
			metricRows.add(standardMetricRow);

			add(standardMetricRow.metricLabel, "growx, pad 0 20 0 0");
			add(standardMetricRow.metricValue, "split 1, right");

			JPanel separator = new JPanel();
			separator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UNDERLINE_BORDER_COLOR));
			add(separator, "span, growx, height 1!, pad 0 20 0 0");
		}

	}

	private void createTitle(String title, String value) {
		add(new TitleLabel(title), "growx");
		add(new TitleLabel(value), "split 1, right");
		JPanel separator = new JPanel();
		separator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UNDERLINE_BORDER_COLOR));
		add(separator, "span, growx, height 1!, pad 0 20 0 0");
		JPanel separator2 = new JPanel();
		separator2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UNDERLINE_BORDER_COLOR));
		add(separator2, "span, growx, height 1!, pad 0 20 0 0");
	}

	public String getExportMetrics() {
		String names = "Description;";
		String values = "BFO;";

		for (int i = 0; i < oquareModelCompatibilityMetrics.size(); i++) {
			names += oquareModelCompatibilityMetrics.get(i).getName() + ";";
			values += oquareModelCompatibilityMetrics.get(i).getValue() + ";";
		}

		for (int i = 0; i < oquareModelFunctionalAdequacyMetrics.size(); i++) {
			names += oquareModelFunctionalAdequacyMetrics.get(i).getName() + ";";
			values += oquareModelFunctionalAdequacyMetrics.get(i).getValue() + ";";
		}

		for (int i = 0; i < oquareModelMaintainabilityMetrics.size(); i++) {
			names += oquareModelMaintainabilityMetrics.get(i).getName() + ";";
			values += oquareModelMaintainabilityMetrics.get(i).getValue() + ";";
		}

		for (int i = 0; i < oquareModelOperabilityMetrics.size(); i++) {
			names += oquareModelOperabilityMetrics.get(i).getName() + ";";
			values += oquareModelOperabilityMetrics.get(i).getValue() + ";";
		}

		for (int i = 0; i < oquareModelReliabilityMetrics.size(); i++) {
			names += oquareModelReliabilityMetrics.get(i).getName() + ";";
			values += oquareModelReliabilityMetrics.get(i).getValue() + ";";
		}

		for (int i = 0; i < oquareModelStructuralMetrics.size(); i++) {
			names += oquareModelStructuralMetrics.get(i).getName() + ";";
			values += oquareModelStructuralMetrics.get(i).getValue() + ";";
		}

		for (int i = 0; i < oquareModelTransferabilityMetrics.size(); i++) {
			names += oquareModelTransferabilityMetrics.get(i).getName() + ";\n";
			values += oquareModelTransferabilityMetrics.get(i).getValue() + ";";
		}

		return names + values;
	}

}
