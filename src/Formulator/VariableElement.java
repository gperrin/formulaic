package Formulator;

public class VariableElement extends FormulaElement {

	private String name;
	private double value;
	private boolean grounded;
	
	public VariableElement(String val){
		name = val;
		grounded = false;
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
	
	public boolean onlyConstants(){
		return false;
	}
	
	public void setValue(double val){
		value = val;
	}
	
	public String toString(){
		return name;
	}
	
	public void	setVariableValue(String	varName, double	value){
		if(this.name.equals(varName) || varName.equals("all")){
			this.value = value;
			grounded = true;
		}
	}
	
	public boolean isFullyGrounded(){
		return this.grounded;
	}
	
	public double evaluate(){
		return value;
	}
	
	public FormulaElement derivate(){
		return new ConstantElement(1);
	}
}


