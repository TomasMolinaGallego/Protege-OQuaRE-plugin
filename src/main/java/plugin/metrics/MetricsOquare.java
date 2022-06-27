package plugin.metrics;

import java.util.Map;

//Class that contains the quality metrics raw and score
public class MetricsOquare {
	private Map<String, Double> metricas;

	public MetricsOquare() {

	}

	public Map<String, Double> getMetricas() {
		return metricas;
	}

	public void setMetricas(Map<String, Double> metricas) {
		this.metricas = metricas;
	}

}
