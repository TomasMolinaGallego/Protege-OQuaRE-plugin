package plugin.view.graphs;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import plugin.metrics.Metric;
import plugin.metrics.OquareModelMetrics;

public class QualitySubcharacteristicsChart extends Chart {

	private String currentMetric;
	private final static String VERTICAL_BAR_CHART_TITLE = "Subcharacteristics metrics";

	private OquareModelMetrics metrics;
	private List<Metric> oquareModelMetrics = new LinkedList<>();
	private List<Metric> oquareModelCompatibilityMetrics = new LinkedList<>();
	private List<Metric> oquareModelFunctionalAdequacyMetrics = new LinkedList<>();
	private List<Metric> oquareModelMaintainabilityMetrics = new LinkedList<>();
	private List<Metric> oquareModelOperabilityMetrics = new LinkedList<>();
	private List<Metric> oquareModelReliabilityMetrics = new LinkedList<>();
	private List<Metric> oquareModelStructuralMetrics = new LinkedList<>();
	private List<Metric> oquareModelTransferabilityMetrics = new LinkedList<>();

	private static final String MODEL_COMPATIBILITY_TITLE = "Compatibility";
	private static final String MODEL_FUNCTIONAL_ADEQUACY_TITLE = "Functional Adequacy";
	private static final String MODEL_MAINTAINABILITY_TITLE = "Maintainability";
	private static final String MODEL_OPERABILITY_TITLE = "Operability";
	private static final String MODEL_REALIABILITY_TITLE = "Reliability";
	private static final String MODEL_STRUCTURAL_TITLE = "Structural";
	private static final String MODEL_TRANSFERABILITY_TITLE = "Transferability";

	public QualitySubcharacteristicsChart(OquareModelMetrics oquareModelMetric, String currentMetric) {
		metrics = oquareModelMetric;
		this.currentMetric = currentMetric;
		createMetrics(metrics.getMapOquareModel(), oquareModelMetrics);
		createMetrics(metrics.getMapOquareModelCompatibility(), oquareModelCompatibilityMetrics);
		createMetrics(metrics.getMapOquareModelFunctionalAdequacy(), oquareModelFunctionalAdequacyMetrics);
		createMetrics(metrics.getMapOquareModelMaintainability(), oquareModelMaintainabilityMetrics);
		createMetrics(metrics.getMapOquareModelOperability(), oquareModelOperabilityMetrics);
		createMetrics(metrics.getMapOquareModelReliability(), oquareModelReliabilityMetrics);
		createMetrics(metrics.getMapOquareModelStructural(), oquareModelStructuralMetrics);
		createMetrics(metrics.getMapOquareModelTransferability(), oquareModelTransferabilityMetrics);
	}

	@Override
	public CategoryDataset createChartDataset() {
		final String rowName = "";

		List<Metric> list = null;
		switch (currentMetric) {
		case MODEL_COMPATIBILITY_TITLE:
			list = oquareModelCompatibilityMetrics;
			break;
		case MODEL_FUNCTIONAL_ADEQUACY_TITLE:
			list = oquareModelFunctionalAdequacyMetrics;
			break;
		case MODEL_MAINTAINABILITY_TITLE:
			list = oquareModelMaintainabilityMetrics;
			break;
		case MODEL_OPERABILITY_TITLE:
			list = oquareModelOperabilityMetrics;
			break;
		case MODEL_REALIABILITY_TITLE:
			list = oquareModelReliabilityMetrics;
			break;
		case MODEL_STRUCTURAL_TITLE:
			list = oquareModelStructuralMetrics;
			break;
		case MODEL_TRANSFERABILITY_TITLE:
			list = oquareModelTransferabilityMetrics;
			break;

		default:
			break;
		}

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.size(); i++) {
			dataset.addValue(list.get(i).getValue(), list.get(i).getName(), rowName);
		}

		return dataset;
	}

	@Override
	public JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(currentMetric, VERTICAL_BAR_CHART_TITLE, "Score", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundPaint(Color.lightGray);

		//Set custom X axis to be centered at 2.5 with 6 values, 3 down 3 up, so it goes to 0 to 5
		CategoryPlot plot = chart.getCategoryPlot();
		NumberAxis numberAxis = new NumberAxis();
		numberAxis.setRangeAboutValue(2.5, 6);
		numberAxis.setNumberFormatOverride(new DecimalFormat("0.00"));
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		plot.setRangeAxis(numberAxis);
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		//Set custom tooltips
		renderer.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator(
		    "{0} ({2})", NumberFormat.getInstance()));

		plot.setFixedRangeAxisSpace(null);

		return chart;
	}

	private void createMetrics(Map<String, Double> map, List<Metric> listMetrics) {
		for (String clave : map.keySet()) {
			Metric metrica = new Metric();
			metrica.setName(clave);
			metrica.setValue(map.get(clave));
			listMetrics.add(metrica);
		}
	}

}
