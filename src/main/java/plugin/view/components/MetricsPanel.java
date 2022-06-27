package plugin.view.components;

import net.miginfocom.swing.MigLayout;
import plugin.metrics.Metric;
import plugin.metrics.OquareMetrics;
import plugin.metrics.OquareModelMetrics;
import plugin.utils.ScrollPane;
import plugin.utils.TooltipsStrings;
import plugin.view.Layout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MetricsPanel extends JTabbedPane implements Layout {

	Logger log = LoggerFactory.getLogger(MetricsPanel.class);

	private List<Metric> oquareMetrics;
	private List<Metric> oquareMetricsScaled;
	private OquareModelMetrics oquareModel;
	private OquareMetrics oquareMetricsObject;

	private MetricsScaledTab metricsOquareTable;
	private MetricsModelTab metricsOquareModelTable;

	private final static String OQUARE_METRICS_TITLE = "Quality metrics ";
	private final static String OQUARE_MODEL_METRICS_TITLE = "Quality characteristics";
	
	public MetricsPanel(OquareMetrics oquareMetrics) {
		oquareMetricsObject = oquareMetrics;
		setBackground(BACKGROUND_COLOR);
		setBorder(BorderFactory.createEmptyBorder());
		initMetrics();
		addPanels();
	}

	private void initMetrics() {
		

		// MetricsOquare
		Map<String, Double> mapaOquare = oquareMetricsObject.getMetricsOquare().getMetricas();
		oquareMetrics = new LinkedList<>();
		createMetrics(mapaOquare, oquareMetrics);

		//MetricsScaled
		oquareMetricsScaled = new LinkedList<>();
		Map<String, Double> mapaScaled = oquareMetricsObject.getMetricsOquareScaled().getMetricas();
		createMetrics(mapaScaled, oquareMetricsScaled);

		//Passing the object to the tab would be many parameters, instead pass the object and inside the tab does the metrics
		oquareModel = oquareMetricsObject.getOquareModelMetrics();

	}

	private void addPanels() {

		metricsOquareTable = new MetricsScaledTab(OQUARE_METRICS_TITLE, oquareMetrics, oquareMetricsScaled);
		metricsOquareModelTable = new MetricsModelTab(OQUARE_MODEL_METRICS_TITLE, oquareModel);

		metricsOquareTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.isPopupTrigger()){
                    handleChangePopupRequest(metricsOquareTable,e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.isPopupTrigger()){
                    handleChangePopupRequest(metricsOquareTable,e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.isPopupTrigger()){
                    handleChangePopupRequest(metricsOquareTable,e);
                }
            }

            private void handleChangePopupRequest(MetricsScaledTab m, MouseEvent e){
                JPopupMenu menu = new JPopupMenu();
                menu.add(new AbstractAction("Export csv to clipboard") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	String metrics = m.getExportMetrics();
                        Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clb.setContents(new StringSelection(metrics),null);
                    }
                });
                menu.show(metricsOquareTable,e.getX(),e.getY());

            }

        });

		metricsOquareModelTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.isPopupTrigger()){
                    handleChangePopupRequest(metricsOquareModelTable,e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.isPopupTrigger()){
                    handleChangePopupRequest(metricsOquareModelTable,e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.isPopupTrigger()){
                    handleChangePopupRequest(metricsOquareModelTable,e);
                }
            }

            private void handleChangePopupRequest(MetricsModelTab m, MouseEvent e){
                JPopupMenu menu = new JPopupMenu();
                menu.add(new AbstractAction("Export csv to clipboard") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	String metrics = m.getExportMetrics();
                        Clipboard clb = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clb.setContents(new StringSelection(metrics),null);
                    }
                });
                menu.show(metricsOquareModelTable,e.getX(),e.getY());

            }

        });
		
		JPanel listViewPanel2 = new JPanel(new MigLayout("wrap 1", "[grow]", "[]20[]"));
		listViewPanel2.setBackground(Color.WHITE);
		listViewPanel2.add(metricsOquareTable, "growx");

		JPanel listViewPanel3 = new JPanel(new MigLayout("wrap 1", "[grow]", "[]20[]"));
		listViewPanel3.setBackground(Color.WHITE);
		listViewPanel3.add(metricsOquareModelTable, "growx");

		addTab(OQUARE_METRICS_TITLE, new ScrollPane(listViewPanel2));
		addTab(OQUARE_MODEL_METRICS_TITLE, new ScrollPane(listViewPanel3));
	}

	private void createMetrics(Map<String, Double> map, List<Metric> listMetrics) {
		for (String clave : map.keySet()) {
			Metric metrica = new Metric();
			metrica.setName(clave);
			metrica.setValue(map.get(clave));
			metrica.setTooltip(TooltipsStrings.TOOLTIPS_MAP.get(clave));
			listMetrics.add(metrica);
		}
	}
	

	public void dispose() {
	}

}
