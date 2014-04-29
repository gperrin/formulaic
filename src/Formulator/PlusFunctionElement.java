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
	
	public FormulaElement derivate(){
		PlusFunctionElement add = new PlusFunctionElement();
		
		// f = a + b      f' = a' + b'
		
		FormulaElement a1 = this.arguments.get(0).derivate();
		FormulaElement b1 = this.arguments.get(1).derivate();
		
		if(a1 instanceof ConstantElement){
			ConstantElement cons = (ConstantElement) a1;
			if(b1 instanceof ConstantElement){
				ConstantElement cons1 = new ConstantElement(cons.getValue() + ((ConstantElement) b1).getValue());
				return cons1;
			}
			if(cons.getValue() == 0){
				return b1;
			}
			
		}
		if(b1 instanceof ConstantElement){
			ConstantElement cons = (ConstantElement) b1;
			if(cons.getValue() == 0){
				return a1;
			}
		}
		add.addArgument(a1,b1);
		
		return add;
	}
	
}
