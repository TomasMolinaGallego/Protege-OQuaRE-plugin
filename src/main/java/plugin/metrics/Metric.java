package plugin.metrics;

//Class that represents a key and a value
public class Metric {
	private String name;
	private double value;
	private String tooltip;
	

	public Metric() {

	}
	
	public Metric(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
}
