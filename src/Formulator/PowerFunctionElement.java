package Formulator;

public class PowerFunctionElement extends FunctionElement {
	
	public boolean needPar(){
		return false;
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
