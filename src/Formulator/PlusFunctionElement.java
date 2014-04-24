package Formulator;

public class PlusFunctionElement extends FunctionElement {
	
	public String toString(){
		String str = new String();
		
		str = super.arguments.get(0).toString();
				
		for(int i = 1; i < arguments.size();i++){
			str += " + " + super.arguments.get(i).toString();
		}
		
		 ;
		
		return str;
	}
	
	public double evaluate(){
		double a;
		
		a = super.arguments.get(0).evaluate();
		
		for(int i = 1; i < arguments.size();i++){
			a += super.arguments.get(i).evaluate();
		}
		
		return a;
	}
	
}
