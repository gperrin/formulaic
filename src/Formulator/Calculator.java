package Formulator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
		System.out.println("To print this message: help");
		System.out.println("To store a formula: store f X+2");
		System.out.println("To evaluate a formula: evaluate f(2) or evaluate f(X=2 Y=g(3))");
		System.out.println("To differentiate a formula: differentiate f");
		System.out.println("To save and load files: files");
		System.out.println("To open the graphic interface: interface");
		System.out.println("To exit: exit");
	}
	
	public static FormulaElement getFormula(int i){
		return (FormulaElement) formulas.get(i).get(1);
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
	
	public static void save(){
        FileChooser.createAndShowGUI();
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
	
	public static void saveFile(File file) throws IOException{
		 String text = new String();
		 for(Vector<Object> v: formulas){
			 FormulaElement f = (FormulaElement) v.get(1);
			 text += "store " + v.get(0) + " " + f.toString() + "\n";
		 }
		 BufferedWriter output = new BufferedWriter(new FileWriter(file));
         output.write(text);
         output.close();
	}
	
	public static void openFile(File file) throws IOException, FileNotFoundException{
		 BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
		 String line;
		 while ((line = reader.readLine()) != null){
			 System.out.println(line);
			 List<String> inst = new ArrayList<String>();
			 inst.add(line);
				
			 parseInstruction(inst);
			 storeFormula(inst);
		 }
		 
		 reader.close();
	}
	
	public static void derivate(List<String> inst){
		String str = inst.get(1);
		char[] ch = str.toCharArray();
		str = "";
		for(int i=0; i<ch.length;i++){
			if(ch[i] != ' ') str += ch[i];
		}
		int index = findFormula(str);
		if(index != -1){
			Vector<Object> v = formulas.get(index);
			Vector<Object> newV = new Vector<Object>();
			
			newV.add(v.get(0) + "'");
			FormulaElement f = (FormulaElement) v.get(1);
			FormulaElement f2 = f.derivate();
			System.out.println(f2.toString());
			newV.add(f2);
			formulas.add(newV);
		}
	}
	
	private static double evaluateFormula(List<String> inst){
		StringTokenizer t = new StringTokenizer(inst.get(1), " (=)", true);
		String formulaName = t.nextToken();
		
		Vector<String> tokens = new Vector<String>();
		
		while(t.hasMoreTokens()){
			tokens.add(t.nextToken());
		}
		
		int index = findFormula(formulaName);
		FormulaElement f = (FormulaElement) formulas.get(index).get(1);
		
		if(index != -1){
			
			for(int i = 0; i < tokens.size() - 1;i++){
				char[] checkToken = tokens.get(i).toCharArray();
				char[] nextCheckToken = tokens.get(i+1).toCharArray();
				if(checkToken[0] == '='){
					if(Character.isDigit(nextCheckToken[0])){
						f.setVariableValue(tokens.get(i-1),Double.parseDouble(tokens.get(i+1)));
					}
					else{
						List<String> newInst = new ArrayList<String>();
						String str = new String();
						int j = i+1;
						newInst.add("evaluate");
						while(!tokens.get(j).equals(")")){
							str += tokens.remove(j);
						}
						str += tokens.remove(j);
						newInst.add(str);
						
						f.setVariableValue(tokens.get(i-1),evaluateFormula(newInst));
					}
									
				}
				else if(checkToken[0] == '('){
					if(Character.isDigit(nextCheckToken[0]) || nextCheckToken[0] == '-'){
						f.setVariableValue("all", Double.parseDouble(tokens.get(i+1)));
					}
					else if (tokens.get(i+2).equals("(")){
						List<String> newInst = new ArrayList<String>();
						String str = new String();
						int j = i+1;
						newInst.add("evaluate");
						while(!tokens.get(j).equals(")")){
							str += tokens.remove(j);
						}
						str += tokens.remove(j);
						newInst.add(str);
						
						f.setVariableValue("all",evaluateFormula(newInst));
					}
				}
			}
		}
		
		if(f.isFullyGrounded()) return f.evaluate();
		else return 0;
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
			case "evaluate":
				System.out.println(evaluateFormula(inst));
				break;
			case "files":
				save();
				break;
			case "differentiate":
				derivate(inst);
				break;
			case "interface":
				@SuppressWarnings("unused")
				Window ui = new Window(600,400);
				break;
			case "exit":
				running = false;
				break;
			default:
				break;
				
			}
			
		}
	   
		
	}

}
