package plugin.view.graphs;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import plugin.metrics.MetricsOquare;
import plugin.utils.ParserXML;

public class QualityMetricsChart extends Chart {

	private MetricsOquare metricsOquare;
	private Color BLUE_OQUARE = new Color(54, 163, 217);
	private final static String HORIZONTAL_BAR_CHART_TITLE = "Quality characteristics chart";

	public QualityMetricsChart(MetricsOquare metricsOquare) {
		this.metricsOquare = metricsOquare;
	}

	@Override
	public CategoryDataset createChartDataset() {

		Map<String, Double> map = metricsOquare.getMetricas();
		final String rowName = "";
		final String[] columnNames = { "TMOnto2", "LCOMOnto", "POnto", "CBOOnto", "TMOnto", "NACOnto", "RROnto",
				"WMCOnto2", "INROnto", "RFCOnto", "PROnto", "NOMOnto", "CROnto", "NOCOnto", "CBOnto2", "DITOnto",
				"ANOnto", "AROnto" };
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < columnNames.length; i++) {
			dataset.addValue(map.get(columnNames[i]), rowName, columnNames[i]);
		}

		return dataset;
	}

	@Override
	public JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(HORIZONTAL_BAR_CHART_TITLE, "Quality characteristics", "Score", dataset,
				PlotOrientation.HORIZONTAL, false, true, true);
		chart.setBackgroundPaint(Color.lightGray);

		CategoryPlot plot = chart.getCategoryPlot();
		NumberAxis numberAxis = new NumberAxis();
		//Set custom X axis to be centered at 2.5 with 6 values, 3 down 3 up, so it goes to 0 to 5
		numberAxis.setRangeAboutValue(2.5, 6);
		numberAxis.setNumberFormatOverride(new DecimalFormat("0.00"));
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		plot.setRangeAxis(numberAxis);
		
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		//Set custom tooltips
		renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator(
		    "Metric {1}, Value {2}", NumberFormat.getInstance()));
		
		
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		//Get the renderer of the chart for changing the color
		renderer.setSeriesPaint(0, BLUE_OQUARE);
		renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator(
		    "{1} ({2})", NumberFormat.getInstance()));
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

		return chart;
	}

}
