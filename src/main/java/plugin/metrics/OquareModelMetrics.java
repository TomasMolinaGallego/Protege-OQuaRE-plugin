package plugin.metrics;

import java.util.Map;

public class OquareModelMetrics {

	private Map<String, Double> mapOquareModel;
	private Map<String, Double> mapOquareModelCompatibility;
	private Map<String, Double> mapOquareModelFunctionalAdequacy;
	private Map<String, Double> mapOquareModelMaintainability;
	private Map<String, Double> mapOquareModelOperability;
	private Map<String, Double> mapOquareModelReliability;
	private Map<String, Double> mapOquareModelStructural;
	private Map<String, Double> mapOquareModelTransferability;

	public OquareModelMetrics() {
	}

	public Map<String, Double> getMapOquareModelCompatibility() {
		return mapOquareModelCompatibility;
	}

	public void setMapOquareModelCompatibility(Map<String, Double> mapOquareModelCompatibility) {
		this.mapOquareModelCompatibility = mapOquareModelCompatibility;
	}

	public Map<String, Double> getMapOquareModelFunctionalAdequacy() {
		return mapOquareModelFunctionalAdequacy;
	}

	public void setMapOquareModelFunctionalAdequacy(Map<String, Double> mapOquareModelFunctionalAdequacy) {
		this.mapOquareModelFunctionalAdequacy = mapOquareModelFunctionalAdequacy;
	}

	public Map<String, Double> getMapOquareModelMaintainability() {
		return mapOquareModelMaintainability;
	}

	public void setMapOquareModelMaintainability(Map<String, Double> mapOquareModelMaintainability) {
		this.mapOquareModelMaintainability = mapOquareModelMaintainability;
	}

	public Map<String, Double> getMapOquareModelOperability() {
		return mapOquareModelOperability;
	}

	public void setMapOquareModelOperability(Map<String, Double> mapOquareModelOperability) {
		this.mapOquareModelOperability = mapOquareModelOperability;
	}

	public Map<String, Double> getMapOquareModelReliability() {
		return mapOquareModelReliability;
	}

	public void setMapOquareModelReliability(Map<String, Double> mapOquareModelReliability) {
		this.mapOquareModelReliability = mapOquareModelReliability;
	}

	public Map<String, Double> getMapOquareModelStructural() {
		return mapOquareModelStructural;
	}

	public void setMapOquareModelStructural(Map<String, Double> mapOquareModelStructural) {
		this.mapOquareModelStructural = mapOquareModelStructural;
	}

	public Map<String, Double> getMapOquareModelTransferability() {
		return mapOquareModelTransferability;
	}

	public void setMapOquareModelTransferability(Map<String, Double> mapOquareModelTransferability) {
		this.mapOquareModelTransferability = mapOquareModelTransferability;
	}

	public void setMapOquareModel(Map<String, Double> mapOquareModel) {
		this.mapOquareModel = mapOquareModel;
	}

	public Map<String, Double> getMapOquareModel() {
		return mapOquareModel;
	}

}
