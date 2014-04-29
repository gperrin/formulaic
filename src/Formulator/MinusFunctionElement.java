package Formulator;

public class MinusFunctionElement extends FunctionElement {
	
	public String toString(){
		String str = new String();
		
		str = super.arguments.get(0).toString();
				
		for(int i = 1; i < arguments.size();i++){
			FormulaElement child = super.arguments.get(i);
			if(child.needPar()){
				str += " - (" + child.toString() +")";
			}
			else{
				str += " - " + child.toString();
			}
		}
		
		 ;
		
		return str;
	}
	
	public double evaluate(){
		double a;
		
		a = super.arguments.get(0).evaluate();
		
		for(int i = 1; i < arguments.size();i++){
			a -= super.arguments.get(i).evaluate();
		}
		
		return a;
	}
	
	public FormulaElement derivate(){
		MinusFunctionElement add = new MinusFunctionElement();
		
		// f = a - b      f' = a' - b'
		
		add.addArgument(this.arguments.get(0).derivate(),this.arguments.get(1).derivate());
		
		return add;
	}
}
