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
	
	public void	setVariableValue(String	varName, double	value){
		arguments.get(0).setVariableValue(varName,value);
	}
	
	public boolean isFullyGrounded(){
		return arguments.get(0).isFullyGrounded();
	}
	
	public double evaluate(){
		return Math.sin(arguments.get(0).evaluate());
	}
	
	public FormulaElement derivate(){
		CosineFunctionElement cos = new CosineFunctionElement();
		MultipleFunctionElement m = new MultipleFunctionElement();
		
		// f = sin(a)      f' = a' * cos(a)
		cos.addArgument(this.arguments.get(0));
		FormulaElement a1 = this.arguments.get(0).derivate();
		
		if(a1 instanceof ConstantElement){
			ConstantElement cons = (ConstantElement) a1;
			if(cons.getValue() == 0){
				return cons;
			}
			if(cons.getValue() == 1){
			
				return cos;
			}
		}
		
		
		m.addArgument(this.arguments.get(0).derivate(),cos);
		
		return m;
	}
}
