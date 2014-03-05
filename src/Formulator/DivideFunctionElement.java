package Formulator;

public class DivideFunctionElement extends FunctionElement {
	
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
}
