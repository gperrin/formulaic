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
}
