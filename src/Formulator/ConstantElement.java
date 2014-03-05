package Formulator;

public class ConstantElement extends FormulaElement {
	
	private double value;
	
	public ConstantElement(double val){
		value = val;
	}
	
	public double getValue(){
		return value;
	}
	
	public String toString(){
		
		String res;
		
		if(value%1 == 0){
			Integer d = (int)value;
			res = d.toString();
		}
		else{
			Double d = new Double(value);
			res = d.toString();
		}
		return res;	
	}
}
