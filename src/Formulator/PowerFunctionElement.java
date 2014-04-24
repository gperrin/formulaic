package Formulator;

public class PowerFunctionElement extends FunctionElement {
	
	public boolean needPar(){
		return false;
	}
	
	public void addArgument(FormulaElement arg1, FormulaElement arg2){
		arguments.clear();
		arguments.add(arg1);
		arguments.add(arg2);
	}
	
	public String toString(){
		String str = new String();
		
		str = super.arguments.get(0).toString() + "^" + super.arguments.get(1).toString();
		
		return str;
	}
	
	public double evaluate(){
		return Math.pow(arguments.get(0).evaluate(),arguments.get(1).evaluate());
	}
	
}
