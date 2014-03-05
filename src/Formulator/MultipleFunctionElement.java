package Formulator;

public class MultipleFunctionElement extends FunctionElement {
	
	
	public boolean needPar(){
		return false;
	}
	
	public String toString(){
		String str = new String();
		
		FormulaElement arg1 =  super.arguments.get(0);
		FormulaElement arg2 =  super.arguments.get(1);
		
		if(arg1 instanceof ConstantElement){
			
			if(arg2 instanceof ConstantElement){
				double dbl = ((ConstantElement) arg1).getValue() * ((ConstantElement) arg2).getValue();
				if(dbl%1 == 0) {
					str = Integer.toString((int)dbl);
				}
				else {
					Double d = new Double(dbl);
					str = d.toString();
				}
			}
			else{
				if(arg2.needPar()){
					str = arg1.toString() + "(" + arg2.toString() + ")";
				}
				else{
					str = arg1.toString() + arg2.toString() ;
				}
				
			}
		}
		else{
			if(arg2 instanceof ConstantElement){
				if(arg1.needPar()){
					str = arg2.toString() + "(" + arg1.toString() + ")";
				}
				else{
					str = arg2.toString() + arg1.toString() ;
				}
			}
			else{
				if(!arg1.needPar() && !arg2.needPar()){
					str = arg1.toString() + "*" + arg2.toString(); 
				}
				else{
					if(arg1.needPar()){
						str = "(" + arg1.toString() +")";
					}
					else{
						str = arg1.toString();
					}
					
					if(arg2.needPar()){
						str += "(" + arg2.toString() +")";
					}
					else{
						str += arg2.toString();
					}
				}
			}
		}
		
		return str;
	}
}
