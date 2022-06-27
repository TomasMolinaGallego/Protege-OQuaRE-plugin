package plugin.view.components;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import plugin.metrics.Metric;
import plugin.view.Layout;

public class MetricsRow implements Layout {
	Metric metric;
	JLabel metricLabel = new JLabel();
	JLabel metricValue = new JLabel();
	JLabel metricScaled = new JLabel();
	private static final DecimalFormat df = new DecimalFormat("0.00");

	public MetricsRow(Metric metric) {
		this.metric = metric;
		metricValue.setHorizontalAlignment(SwingConstants.RIGHT);
		metricLabel.setText(metric.getName());
		metricValue.setText(df.format(metric.getValue()) + "");
		metricLabel.setToolTipText(metric.getTooltip());
		
	}
	
	public MetricsRow(Metric metric, Metric metricScaled) {
		this(metric);
		this.metricScaled.setText(df.format(metricScaled.getValue()) + "");
	}
}