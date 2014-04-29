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
	
	public FormulaElement derivate(){
		DivideFunctionElement div = new DivideFunctionElement();
		PowerFunctionElement pow = new PowerFunctionElement();
		MinusFunctionElement minus = new MinusFunctionElement();
		MultipleFunctionElement m1 = new MultipleFunctionElement();
		MultipleFunctionElement m2 = new MultipleFunctionElement();
		ConstantElement c = new ConstantElement(2);
		
		// f = a/b      f' = (a'b - ab')/b²
		pow.addArgument(this.arguments.get(1),c);
		m1.addArgument(this.arguments.get(0).derivate());
		m1.addArgument(this.arguments.get(1));
		
		m2.addArgument(this.arguments.get(0));
		m2.addArgument(this.arguments.get(1).derivate());
		
		minus.addArgument(m1,m2);
		
		div.addArgument(minus,pow);
		
		return div;
	}
}
