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
	
	public void	setVariableValue(String	varName, double	value){
		arguments.get(0).setVariableValue(varName,value);
	}
	
	public boolean isFullyGrounded(){
		return arguments.get(0).isFullyGrounded();
	}
	
	public double evaluate(){
		return Math.cos(arguments.get(0).evaluate());
	}
	
	public FormulaElement derivate(){
		SineFunctionElement sin = new SineFunctionElement();
		MultipleFunctionElement m1 = new MultipleFunctionElement();
		MultipleFunctionElement m2 = new MultipleFunctionElement();
		ConstantElement c = new ConstantElement(-1);
		
		// f = cos(a)      f' = -a' * sin(a)
		
		sin.addArgument(this.arguments.get(0));
		FormulaElement a1 = this.arguments.get(0).derivate();
		
		if(a1 instanceof ConstantElement){
			ConstantElement cons = (ConstantElement) a1;
			if(cons.getValue() == 0){
				return cons;
			}
			if(cons.getValue() == 1){
				m2.addArgument(c,sin);
				
				return m2;
			}
		}
		
		m1.addArgument(a1,sin);
		m2.addArgument(c,m1);
		
		return m2;
	}
}
