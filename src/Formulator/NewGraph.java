package Formulator;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class NewGraph {  
	
	private Vector<FormulaElement> formulaList = new Vector<FormulaElement>();
	private Vector<Triple> limitSet = new Vector<Triple>();
	public JFreeChart thisChart;
	
	public void addFormulaToGraph(FormulaElement f){
		formulaList.add(f);
	}
	
	public void addLimitSet(Triple t){
		limitSet.add(t);
	}
	
	public void drawGraph(){
		
		XYSeriesCollection formula1set = new XYSeriesCollection();
		
		for (int i = 0; i < formulaList.size(); i++) {
			XYSeries xy = new XYSeries(formulaList.get(i).toString());
			double x = limitSet.get(i).from;
			double y;
			double c = limitSet.get(i).from;
			y = formulaList.get(i).evaluate(/** For x ***/);;
			for (double j = limitSet.get(i).from; j < limitSet.get(i).to + limitSet.get(i).increment;
					j+= limitSet.get(i).increment) {
				formulaList.get(i).setVariableValue("X", c);
				y = formulaList.get(i).evaluate();
				xy.add(x,y);
				x += limitSet.get(i).increment;
				c += limitSet.get(i).increment;
			}
			formula1set.addSeries(xy);
		}
		
		JFreeChart formulaGraph = ChartFactory.createXYLineChart("Graph of Equations","x","y",formula1set,PlotOrientation.VERTICAL,true,false,false);
		formulaGraph.setBackgroundPaint(Color.lightGray); 
		XYPlot formulaGraphPlot = (XYPlot)formulaGraph.getPlot();
		formulaGraphPlot.setBackgroundPaint (Color.white);
		formulaGraphPlot.setDomainGridlinePaint (Color.GREEN);
		formulaGraphPlot.setRangeGridlinePaint (Color.GREEN);
		formulaGraphPlot.setAxisOffset (new RectangleInsets(50, 0, 20, 5));
		formulaGraphPlot.setDomainCrosshairVisible(true);
		formulaGraphPlot.setRangeCrosshairVisible(true);
		XYLineAndShapeRenderer render = (XYLineAndShapeRenderer)formulaGraphPlot.getRenderer();      
		render.setBaseShapesVisible(true);
		render.setBaseShapesFilled (true);
		this.thisChart = formulaGraph;
		
		ChartFrame frame = new ChartFrame("Frame", formulaGraph);
		frame.setSize(900,900);
		frame.setVisible(true); 
	}
	 
	public void save(){		
		try {
			ChartUtilities.saveChartAsJPEG(new File("NewGraph.jpeg"), this.thisChart, 900, 900);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}