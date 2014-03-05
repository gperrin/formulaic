package Formulator;

public class MinusFunctionElement extends FunctionElement {
	
	public String toString(){
		String str = new String();
		
		str = super.arguments.get(0).toString() + " - " + super.arguments.get(1).toString();
		
		return str;
	}
}
