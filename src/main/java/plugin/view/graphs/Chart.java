package plugin.view.graphs;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

public abstract class Chart extends JPanel{
	
	
	public ChartPanel createTabChart() {
		
		CategoryDataset dataset = createChartDataset();
		JFreeChart chart = createChart(dataset);

		return new ChartPanel(chart);
		
	}
	
	public abstract CategoryDataset createChartDataset();
	public abstract JFreeChart createChart(CategoryDataset dataset);
}
