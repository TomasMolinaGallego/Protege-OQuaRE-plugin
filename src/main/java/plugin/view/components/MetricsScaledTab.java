package plugin.view.components;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.miginfocom.swing.MigLayout;
import plugin.metrics.Metric;
import plugin.utils.TitleLabel;
import plugin.view.Layout;

//Class to create a tab with 3 columns. It needs a title and 2 list of metrics. 
//Both lists must have the same order and the same length. 
public class MetricsScaledTab extends JPanel implements Layout {
	Logger log = LoggerFactory.getLogger(MetricsScaledTab.class);

	private static final DecimalFormat nf = new DecimalFormat("0.000");

	private List<Metric> metrics = new LinkedList<>();
	private List<Metric> metricsScaled = new LinkedList<>();

	private List<MetricsRow> metricRows = new ArrayList<>();

	public MetricsScaledTab(String title, List<Metric> metrics, List<Metric> metricsScaled) {
		this.metrics = metrics;
		this.metricsScaled = metricsScaled;
		setLayout(new MigLayout("wrap 3, insets 0, gap 1px", "[grow]1[grow]"));
		setBackground(BACKGROUND_COLOR);
		add(new TitleLabel(title), "growx");
		add(new TitleLabel("Raw value (actual score)"), "growx");
		add(new TitleLabel("Quality score (actual scaled)"), "growx");

		addMetrics();
	}

	private void addMetrics() {

		// Create rows from metrics
		for (int i = 0; i < metrics.size(); i++) {
			createMetricRow(metrics.get(i), metricsScaled.get(i));
		}

	}

	private void createMetricRow(Metric metric, Metric metricScaled) {

		MetricsRow standardMetricRow = new MetricsRow(metric, metricScaled);
		metricRows.add(standardMetricRow);

		add(standardMetricRow.metricLabel, "growx, pad 0 20 0 0");
		add(standardMetricRow.metricValue);
		add(standardMetricRow.metricScaled);

		JPanel separator = new JPanel();
		separator.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UNDERLINE_BORDER_COLOR));
		add(separator, "span, growx, height 1!, pad 0 20 0 0");
	}

	public String getExportMetrics() {		
		String names = "";
		String values = "";
		String scaled = "";

		names += "Description;";
		values += "BFO;";
		scaled += "BFO;";

		for (int i = 0; i < metrics.size(); i++) {
			names += metrics.get(i).getName() + ";";
			values += metrics.get(i).getValue() + ";";
			scaled += metricsScaled.get(i).getValue() + ";";
		}
		names += "\n";
		values += "\n";
		
		return names + values + scaled;

	
	}
}
