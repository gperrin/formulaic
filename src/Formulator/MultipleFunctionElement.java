package Formulator;

import java.util.ArrayList;
import java.util.List;

public class MultipleFunctionElement extends FunctionElement {
	
	
	public boolean needPar(){
		return false;
	}
	
	public String toString(){
		String str = new String();
		
		List<FormulaElement> c = new ArrayList<FormulaElement>();
		List<FormulaElement> v = new ArrayList<FormulaElement>();
		List<FormulaElement> f = new ArrayList<FormulaElement>();

		for(FormulaElement fe: arguments){
			if(fe instanceof ConstantElement) c.add(fe);
			if(fe instanceof VariableElement) v.add(fe);
			if(fe instanceof FunctionElement) f.add(fe);
		}
		
		if(c.size() > 0){
			str += c.get(0).toString();
			for(int i = 1; i < c.size();i++){
				str += "*" + c.get(i).toString();
			}
			
			if(v.size() > 0){
				for(int i = 0; i < v.size();i++){
					str += "*" + v.get(i).toString();
				}
				
				if(f.size() > 0){
					boolean hadPar = false;
					for(int i = 0; i < f.size();i++){
						if (f.get(i).needPar()){
							str += "(" + f.get(i).toString() + ")";
							hadPar = true;
						}
						else {
							if(hadPar) {
								str += f.get(i).toString();
							}
							else{
								str += "*" + f.get(i).toString();
							}
							hadPar = false;
						}
					}
				}
			}
			else if(f.size() > 0){
				boolean hadPar = false;
				for(int i = 0; i < f.size();i++){
					if (f.get(i).needPar()){
						str += "(" + f.get(i).toString() + ")";
						hadPar = true;
					}
					else {
						if(hadPar) {
							str += f.get(i).toString();
						}
						else{
							str += "*" + f.get(i).toString();
						}
						hadPar = false;
					}
				}
			}
		}
		else{
			if(v.size() > 0){
				str += v.get(0).toString();
				for(int i = 1; i < v.size();i++){
					str += "*" + v.get(i).toString();
				}
				
				if(f.size() > 0){
					boolean hadPar = false;
					for(int i = 0; i < f.size();i++){
						if (f.get(i).needPar()){
							str += "(" + f.get(i).toString() + ")";
							hadPar = true;
						}
						else {
							if(hadPar) {
								str += f.get(i).toString();
							}
							else{
								str += "*" + f.get(i).toString();
							}
							hadPar = false;
						}
					}
				}
			}
			else{
				if(f.size() > 0){
					boolean hadPar = false;
					
					if (f.get(0).needPar()){
						str += "(" + f.get(0).toString() + ")";
						hadPar = true;
					}
					else {
						hadPar = false;
						str += f.get(0).toString();
					}
					
					for(int i = 1; i < f.size();i++){
						if (f.get(i).needPar()){
							str += "(" + f.get(i).toString() + ")";
							hadPar = true;
						}
						else {
							if(hadPar) {
								str += f.get(i).toString();
							}
							else{
								str += "*" + f.get(i).toString();
							}
							hadPar = false;
						}
					}
				}
			}
		}
		
		return str;
	}
	
	public double evaluate(){
		double a;
		
		a = super.arguments.get(0).evaluate();
		
		for(int i = 1; i < arguments.size();i++){
			a = a * super.arguments.get(i).evaluate();
		}
		
		return a;
	}
	
	public FormulaElement derivate(){
		PlusFunctionElement add = new PlusFunctionElement();
		MultipleFunctionElement m1 = new MultipleFunctionElement();
		MultipleFunctionElement m2 = new MultipleFunctionElement();
		
		// f = ab      f' = a'b + ab'
		m1.addArgument(this.arguments.get(0).derivate());
		m1.addArgument(this.arguments.get(1));
		
		m2.addArgument(this.arguments.get(0));
		m2.addArgument(this.arguments.get(1).derivate());
		
		add.addArgument(m1,m2);
		
		return add;
	}
}
