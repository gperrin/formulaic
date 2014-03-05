package Formulator;

public class CosineFunctionElement extends FunctionElement {
	
	public void addArgument(FormulaElement arg){
		super.arguments.clear();
		super.arguments.add(arg);
	}
	
	public boolean needPar(){
		return false;
	}
	
	public String toString(){
		String var = super.arguments.get(0).toString();
		
		String res = "cos(" + var + ")";
		return res;
	}
}
