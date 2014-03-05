package Formulator;

public class VariableElement extends FormulaElement {

	private String name;
	private double value;
	
	public VariableElement(String val){
		name = val;
	}
	
	public boolean needPar(){
		return false;
	}
	
	public String getName(){
		return name;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double val){
		value = val;
	}
	
	public String toString(){
		return name;
	}
}
