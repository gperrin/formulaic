package Formulator;

import java.util.ArrayList;
import java.util.List;

public class FunctionElement extends FormulaElement {
	
	protected List<FormulaElement> arguments = new ArrayList<FormulaElement>();
	
	public void addArgument(FormulaElement... arg){
		for(FormulaElement f: arg){
			arguments.add(f);
		}
	}
	
	public List<FormulaElement> getArguments(){
		return arguments;
	}
	
	public void	setVariableValue(String	varName, double	value){
		for(int i = 0; i < arguments.size();i++){
			arguments.get(i).setVariableValue(varName,value);
		}
	}
	
	public boolean isFullyGrounded(){
		for(int i = 0; i < arguments.size();i++){
			if(!arguments.get(i).isFullyGrounded()){
				return false;
			}
		}
		return true;
	}

}
