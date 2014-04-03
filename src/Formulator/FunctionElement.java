package Formulator;

import java.util.ArrayList;
import java.util.List;

public class FunctionElement extends FormulaElement {
	
	protected List<FormulaElement> arguments = new ArrayList<FormulaElement>();
	
	public void addArgument(FormulaElement arg1, FormulaElement arg2){
		arguments.clear();
		arguments.add(arg1);
		arguments.add(arg2);
	}
	
	public List<FormulaElement> getArguments(){
		return arguments;
	}
	
	public void	setVariableValue(String	varName, double	value){
		arguments.get(0).setVariableValue(varName,value);
		arguments.get(1).setVariableValue(varName,value);
	}
	
	public boolean isFullyGrounded(){
		return (arguments.get(0).isFullyGrounded() && arguments.get(1).isFullyGrounded());
	}

}
