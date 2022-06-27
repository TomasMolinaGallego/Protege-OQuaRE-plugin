package plugin.metrics;


//Father class that contains all the metrics
public class OquareMetrics {
	private MetricsOquare metricsOquare;
	private MetricsOquare metricsOquareScaled;
	private OquareModelMetrics oquareModelMetrics;

	public OquareMetrics() {

	}

	public OquareModelMetrics getOquareModelMetrics() {
		return oquareModelMetrics;
	}

	public void setOquareModelMetrics(OquareModelMetrics oquareModelMetrics) {
		this.oquareModelMetrics = oquareModelMetrics;
	}

	public MetricsOquare getMetricsOquare() {
		return metricsOquare;
	}

	public void setMetricsOquare(MetricsOquare metricsOquare) {
		this.metricsOquare = metricsOquare;
	}

	public MetricsOquare getMetricsOquareScaled() {
		return metricsOquareScaled;
	}

	public void setMetricsOquareScaled(MetricsOquare metricsOquareScaled) {
		this.metricsOquareScaled = metricsOquareScaled;
	}

}
