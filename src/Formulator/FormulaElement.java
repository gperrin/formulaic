package Formulator;

import java.util.StringTokenizer;
import java.util.Vector;

public class FormulaElement {
	
	
	
	public boolean needPar(){
		return true;
	}
	
	public static FormulaElement parseFormula(String text){
		FormulaElement formula = new FormulaElement();
		
		StringTokenizer tokenizer = new StringTokenizer(text, "+-/^() \t", true); 
		
		Vector<Object> tokens = new Vector<Object>();
		
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			if(token != " " && token != "/t"){
				char[] checkToken = token.toCharArray();
				if(Character.isDigit(checkToken[0])){   // first char is a digit
					
					int last = checkToken.length - 1;
					if(Character.isLetter(checkToken[last])){    // last char is not a digit
						String token1 = new String();
						String token2 = new String();
						for(int i = 0;i<=last;i++){
							if(Character.isDigit(checkToken[i]) || checkToken[i] == '.'){
								token1+=checkToken[i];
							}
							else{
								token2+=checkToken[i];
							}
						}
						tokens.add(token1);
						tokens.add(token2);
					}
					else{
						tokens.add(token);
					}
				}
				else{
					tokens.add(token);
				}
			}
		}

		// Pass 1
		
		for(int i=0; i<tokens.size();i++){
			
			String currentToken = (String)tokens.get(i);
			Character a = currentToken.toCharArray()[0];

			if(Character.isDigit(a)){
				Double d = new Double(currentToken);
				ConstantElement c = new ConstantElement(d);
				tokens.remove(i);
				tokens.add(i,c);
			}
			else{
				if(Character.isLetter(a)){
					if(!currentToken.equals("cos")  && !currentToken.equals("sin")){
						VariableElement v = new VariableElement(currentToken);
						tokens.remove(i);
						tokens.add(i,v);
					}
				}
			}
		}
		
		// Pass 2
		
		for(int i=1; i<tokens.size();i++){
			if(tokens.get(i) instanceof VariableElement){
				if((tokens.get(i-1) instanceof VariableElement) || (tokens.get(i-1) instanceof ConstantElement)){
					MultipleFunctionElement m = new MultipleFunctionElement();
					m.addArgument((FormulaElement)tokens.remove(i-1), (FormulaElement)tokens.remove(i-1));
					tokens.add(i-1,m);
				}
			}
			
		}
		
		// Pass 3
		
		for(int i=0; i<tokens.size();i++){
			if(tokens.get(i).equals("(")){
				if(i>0 && tokens.get(i-1).equals("cos")){
					String s = new String();
					int countPar = 1;
					while(!tokens.get(i+1).equals(")") || countPar!=1){
						if(tokens.get(i+1) instanceof String){
							if(tokens.get(i+1).equals("(")){
								countPar++;
							}
							if(tokens.get(i+1).equals(")")){
								countPar--;
							}
							s += tokens.remove(i+1);
						}
						else{
							FormulaElement f = (FormulaElement)tokens.remove(i+1);
							s += f.toString();
						}
					}
					FormulaElement fe = new FormulaElement();
					fe = parseFormula(s);
					
					CosineFunctionElement c = new CosineFunctionElement();
					c.addArgument(fe);
					tokens.remove(i+1);   // Right parenthese
					tokens.remove(i);   // Left parenthese
					tokens.remove(i-1);   // cos
					tokens.add(i-1,c);
				}
				else if(i>0 && tokens.get(i-1).equals("sin")){
					String s = new String();
					int countPar = 1;
					while(!tokens.get(i+1).equals(")") || countPar!=1){
						if(tokens.get(i+1) instanceof String){
							if(tokens.get(i+1).equals("(")){
								countPar++;
							}
							if(tokens.get(i+1).equals(")")){
								countPar--;
							}
							s += tokens.remove(i+1);
						}
						else{
							FormulaElement f = (FormulaElement)tokens.remove(i+1);
							s += f.toString();
						}
					}
					FormulaElement fe = new FormulaElement();
					fe = parseFormula(s);
					
					SineFunctionElement c = new SineFunctionElement();
					c.addArgument(fe);
					tokens.remove(i+1);   // Right parenthese
					tokens.remove(i);   // Left parenthese
					tokens.remove(i-1);   // cos
					tokens.add(i-1,c);
				}
				else{
					String s = new String();
					int countPar = 1;
					while(!tokens.get(i+1).equals(")") || countPar!=1){
						if(tokens.get(i+1) instanceof String){
							if(tokens.get(i+1).equals("(")){
								countPar++;
							}
							if(tokens.get(i+1).equals(")")){
								countPar--;
							}
							s += tokens.remove(i+1);
						}
						else{
							FormulaElement f = (FormulaElement)tokens.remove(i+1);
							s += f.toString();
						}
					}
					FormulaElement fe = new FormulaElement();
					fe = parseFormula(s);
					tokens.remove(i+1);   // Right parenthese
					tokens.remove(i);   // Left parenthese
					tokens.add(i,fe);
				}
				
			}
		}
		
		// Pass 4
		
		for(int i=1; i<tokens.size();i++){
			if(tokens.get(i) instanceof String){
				String currentToken = (String)tokens.get(i);
				char ch = currentToken.toCharArray()[0];
				if(ch == '^'){
					PowerFunctionElement p = new PowerFunctionElement();
					p.addArgument((FormulaElement)tokens.remove(i-1),(FormulaElement)tokens.remove(i));
					tokens.remove(i-1);   // ^ 
					tokens.add(i-1,p);
				}
			}
		}
		
		// Pass 5
		
		for(int i=1; i<tokens.size();i++){
			if(tokens.get(i) instanceof String){
				String currentToken = (String)tokens.get(i);
				char ch = currentToken.toCharArray()[0];
				if(ch == '/'){
					DivideFunctionElement d = new DivideFunctionElement();
					d.addArgument((FormulaElement)tokens.remove(i-1),(FormulaElement)tokens.remove(i));
					tokens.remove(i-1);   //    /
					tokens.add(i-1,d);
				}
			}
			else{
				if(tokens.get(i-1) instanceof FormulaElement){
					MultipleFunctionElement m = new MultipleFunctionElement();
					m.addArgument((FormulaElement)tokens.remove(i-1),(FormulaElement)tokens.remove(i-1));
					tokens.add(i-1,m);
				}
			}
		}
		
		// Pass 6
		
		for(int i=1; i<tokens.size();i++){
			if(tokens.get(i) instanceof String){
				String currentToken = (String)tokens.get(i);
				char ch = currentToken.toCharArray()[0];
				if(ch == '+'){
					PlusFunctionElement p = new PlusFunctionElement();
					p.addArgument((FormulaElement)tokens.remove(i-1),(FormulaElement)tokens.remove(i));
					tokens.remove(i-1);   //    +
					tokens.add(i-1,p);
				}
				else if(ch == '-'){
					MinusFunctionElement m = new MinusFunctionElement();
					m.addArgument((FormulaElement)tokens.remove(i-1),(FormulaElement)tokens.remove(i));
					tokens.remove(i-1);   //    +
					tokens.add(i-1,m);
				}
			}
		}
		
		formula = (FormulaElement)tokens.get(0);
		
		return formula;
	}

	public static void main(String[] args){
		
		String str1 = "2X+Y/X^2";
		System.out.println(parseFormula(str1).toString());
		
		String str2 = "cos(3X)/2^sin(X)";
		System.out.println(parseFormula(str2).toString());
		
		String str3 = "(X+2)(X–(Y^3+7)+cos(2^X))";
		System.out.println(parseFormula(str3).toString());
		
	}
}
