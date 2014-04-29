package Formulator;

public class PowerFunctionElement extends FunctionElement {
	
	public boolean needPar(){
		return false;
	}
	
	public void addArgument(FormulaElement arg1, FormulaElement arg2){
		arguments.clear();
		arguments.add(arg1);
		arguments.add(arg2);
	}
	
	public String toString(){
		String str = new String();
		
		str = super.arguments.get(0).toString() + "^" + super.arguments.get(1).toString();
		
		return str;
	}
	
	public double evaluate(){
		return Math.pow(arguments.get(0).evaluate(),arguments.get(1).evaluate());
	}
	
	public FormulaElement derivate(){
		if(this.arguments.get(1) instanceof ConstantElement){
			PowerFunctionElement pow = new PowerFunctionElement();
			MultipleFunctionElement m1 = new MultipleFunctionElement();
			MultipleFunctionElement m2 = new MultipleFunctionElement();
			
			ConstantElement c = (ConstantElement)this.arguments.get(0);
			ConstantElement c2 = new ConstantElement(c.getValue() - 1);
			
			// f = a^b      f' = b * a' * a^(b-1)  with b an integer
			pow.addArgument(this.arguments.get(0),c2);
			
			m1.addArgument(this.arguments.get(0).derivate());
			m1.addArgument(this.arguments.get(1));
			
			m2.addArgument(m1);
			m2.addArgument(pow);
			
			return m2;
		}
		else{
			System.out.println("Too difficult to derivate. The power function is not taken into account");
			return this.arguments.get(0).derivate();
		}
	}
	
}
