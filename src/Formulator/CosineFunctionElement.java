package Formulator;

public class CosineFunctionElement extends FunctionElement {
	
	public void addArgument(FormulaElement arg){
		super.arguments.clear();
		super.arguments.add(arg);
	}
	
	public boolean needPar(){
		return false;
	}
	
	public String toString(){
		String var = super.arguments.get(0).toString();
		
		String res = "cos(" + var + ")";
		return res;
	}
	
	public void	setVariableValue(String	varName, double	value){
		arguments.get(0).setVariableValue(varName,value);
	}
	
	public boolean isFullyGrounded(){
		return arguments.get(0).isFullyGrounded();
	}
	
	public double evaluate(){
		return Math.cos(arguments.get(0).evaluate());
	}
}
