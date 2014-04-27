package Formulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Calculator {
	
	static List<Vector<Object>> formulas;
	static BufferedReader br;
	
	public static void printHelp(){
		System.out.println("To store a formula: store f X+2");
	}
	
	public static Integer findFormula(String name){
		for(int i = 0; i< formulas.size();i++){
			Vector<Object> v = formulas.get(i);
			if(v.get(0).equals(name)){
				return i;
			}
		}
		
		return -1;
	}
	
	private static void storeFormula(List<String> inst) throws IOException {
		inst.remove(0);
		parseInstruction(inst);
		
		Vector<Object> v = new Vector<Object>();
		if(findFormula(inst.get(0)) == -1){
			v.add(inst.get(0));  // name of the formula
			v.add(FormulaElement.parseFormula(inst.get(1)));		// formulaElement
			
			formulas.add(v);
		}
		else{
			System.out.println("This name already exist. Do you want to overwrite the formula (Y/N)");
			String answer = br.readLine();
			if(answer == "Y"){
				v.add(inst.get(0));  // name of the formula
				v.add(FormulaElement.parseFormula(inst.get(1)));		// formulaElement
				
				formulas.add(v);
			}
		}
		
	}

	public static void parseInstruction(List<String> inst){
		StringTokenizer tokenizer = new StringTokenizer(inst.remove(0), " "); 
		
		if(tokenizer.hasMoreTokens()) inst.add(tokenizer.nextToken());
		
		String arguments = new String();
		while(tokenizer.hasMoreTokens()){
			arguments += tokenizer.nextToken() + " ";
		}
		
		inst.add(arguments);
	}
	
	public static void main(String[] args) throws IOException{
		
		formulas = new ArrayList<Vector<Object>>();
		
		System.out.println("Welcome to Formulaic !");
		boolean running = true;		
		br = new BufferedReader(new InputStreamReader(System.in));
		String instruction = new String();

		while(running){
			instruction = br.readLine();
			List<String> inst = new ArrayList<String>();
			inst.add(instruction);
			
			parseInstruction(inst);
			
			switch (inst.get(0)) {
			case "help":
				printHelp();			
				break;
			case "store":
				storeFormula(inst);
				break;
			default:
				break;
				
			}
			
		}
	   
		
	}

}
