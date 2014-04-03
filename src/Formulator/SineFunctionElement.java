package Formulator;

public class SineFunctionElement extends FunctionElement {

	public void addArgument(FormulaElement arg){
		super.arguments.clear();
		super.arguments.add(arg);
	}
	
	
	public boolean needPar(){
		return false;
	}
	
	public String toString(){
		String var = super.arguments.get(0).toString();
		
		String res = "sin(" + var + ")";
		return res;
	}
	
	public void	setVariableValue(String	varName, double	value){
		arguments.get(0).setVariableValue(varName,value);
	}
	
	public boolean isFullyGrounded(){
		return arguments.get(0).isFullyGrounded();
	}
	
	public double evaluate(){
		return Math.sin(arguments.get(0).evaluate());
	}
}
