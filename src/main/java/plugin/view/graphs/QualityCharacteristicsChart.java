package plugin.view.graphs;

import java.awt.Color;
import java.text.NumberFormat;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import plugin.metrics.OquareModelMetrics;
import plugin.utils.SpiderWebPlotPatched;

public class QualityCharacteristicsChart extends Chart {
	
	private OquareModelMetrics oquareModelMetrics;
	private Color BLUE_OQUARE = new Color(54, 163, 217);
	
	public QualityCharacteristicsChart(OquareModelMetrics oquareModelMetrics) {
		this.oquareModelMetrics = oquareModelMetrics;
	}

	@Override
	public CategoryDataset createChartDataset() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		final String rowName = "Metrics";
		final String[] columnName = { "Compatibility", "Functional Adequacy", "Maintanability", "Operability",
				"Reliability", "Structural", "Transferability" };

		dataset.addValue(oquareModelMetrics.getMapOquareModelCompatibility().get("Compatibility"), rowName, columnName[0]);
		dataset.addValue(oquareModelMetrics.getMapOquareModelFunctionalAdequacy().get("FunctionalAdequacy"), rowName,
				columnName[1]);
		dataset.addValue(oquareModelMetrics.getMapOquareModelMaintainability().get("Maintainability"), rowName, columnName[2]);
		dataset.addValue(oquareModelMetrics.getMapOquareModelOperability().get("Operability"), rowName, columnName[3]);
		dataset.addValue(oquareModelMetrics.getMapOquareModelReliability().get("Reliability"), rowName, columnName[4]);
		dataset.addValue(oquareModelMetrics.getMapOquareModelStructural().get("Structural"), rowName, columnName[5]);
		dataset.addValue(oquareModelMetrics.getMapOquareModelTransferability().get("Transferability"), rowName, columnName[6]);

		return dataset;
	}

	@Override
	public JFreeChart createChart(CategoryDataset dataset) {
		SpiderWebPlot plot = SpiderWebPlotPatched.createSpiderWebPlotPatched(dataset);
		// 5 is the max value of a characteristic can have
		plot.setMaxValue(5);
		// Tooltip for showing the values of each characteristic
		StandardCategoryToolTipGenerator tooltipGenerator = new StandardCategoryToolTipGenerator("{1} ({2})",NumberFormat.getInstance());
		plot.setToolTipGenerator(tooltipGenerator);
		//
		plot.setSeriesPaint(0, BLUE_OQUARE);
		JFreeChart chart = new JFreeChart("Oquare Model Chart", plot);
		chart.removeLegend();
		return chart;
	}

}
