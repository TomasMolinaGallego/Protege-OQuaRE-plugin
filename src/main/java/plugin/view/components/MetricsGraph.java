package plugin.view.components;

import plugin.metrics.OquareMetrics;
import plugin.utils.ParserXML;
import plugin.view.Layout;
import plugin.view.graphs.QualityMetricsChart;
import plugin.view.graphs.QualityCharacteristicsChart;
import plugin.view.graphs.QualitySubcharacteristicsChart;

import org.jfree.chart.ChartPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetricsGraph extends JTabbedPane implements Layout, ActionListener {
	Logger log = LoggerFactory.getLogger(ParserXML.class);

	
	private final static String HORIZONTAL_BAR_CHART_TITLE = "Quality metrics charts";
	private final static String SPIDER_CHART_TITLE = "Quality characteristics chart";
	private final static String VERTICAL_BAR_CHART_TITLE = "Quality subcharacteristics charts";
	
	private OquareMetrics oquareMetrics;

	JPanel panel = new JPanel();



	public MetricsGraph(OquareMetrics oquareMetrics) {
		this.oquareMetrics = oquareMetrics;
		
		String[] optionsToChoose = {"Compatibility", "Functional Adequacy", "Maintainability", "Operability", "Reliability", "Structural", "Transferability"};

		QualityMetricsChart horizontalBarChart = new QualityMetricsChart(oquareMetrics.getMetricsOquareScaled());
		addTab(HORIZONTAL_BAR_CHART_TITLE, horizontalBarChart.createTabChart());
		
		
		QualityCharacteristicsChart spiderWebChart = new QualityCharacteristicsChart(oquareMetrics.getOquareModelMetrics());
		addTab(SPIDER_CHART_TITLE, spiderWebChart.createTabChart());
		
		JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
		jComboBox.addActionListener((ActionListener) this);
		QualitySubcharacteristicsChart verticalBarChart = new QualitySubcharacteristicsChart(oquareMetrics.getOquareModelMetrics(), "Compatibility");
		ChartPanel chartPanel = verticalBarChart.createTabChart();
		panel.add(chartPanel);
		panel.add(jComboBox);
		addTab(VERTICAL_BAR_CHART_TITLE, panel);

	}
	
	public void actionPerformed(ActionEvent e) {
		JComboBox comboBox = (JComboBox)e.getSource();
		panel.remove(0);
		QualitySubcharacteristicsChart verticalBarChart = new QualitySubcharacteristicsChart(oquareMetrics.getOquareModelMetrics(), comboBox.getSelectedItem()+"");
		ChartPanel chartPanel = verticalBarChart.createTabChart();
		
		panel.add(chartPanel, 0);
		panel.repaint();
		panel.revalidate();
	}
	
	public void dispose() {
	}



}
