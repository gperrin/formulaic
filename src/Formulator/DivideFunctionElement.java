package Formulator;

public class DivideFunctionElement extends FunctionElement {
	
	public void addArgument(FormulaElement arg1, FormulaElement arg2){
		arguments.clear();
		arguments.add(arg1);
		arguments.add(arg2);
	}
	
	public String toString(){
		String str = new String();
		
		FormulaElement arg1 =  super.arguments.get(0);
		FormulaElement arg2 =  super.arguments.get(1);
		
		if(arg1.needPar()){
			str = "(" + arg1.toString() +")";
		}
		else{
			str = arg1.toString();
		}
		
		str += "/";
		
		if(arg2.needPar()){
			str += "(" + arg2.toString() +")";
		}
		else{
			str += arg2.toString();
		}
		
		return str;
	}
	
	public double evaluate(){
		return (arguments.get(0).evaluate() / arguments.get(1).evaluate());
	}
}
