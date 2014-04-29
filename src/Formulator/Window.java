package Formulator;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Frame
	JFrame window = new JFrame();
	//Panels
	JPanel frame= new JPanel();
	JPanel panelEq= new JPanel();
	JPanel panelX= new JPanel();
	JPanel panelY= new JPanel();
	JPanel panelResult = new JPanel();
	JPanel panelButton= new JPanel();
	
	//Labels
	JLabel equation = new JLabel();
	JLabel setX = new JLabel();
	JLabel setY = new JLabel();
	JLabel seeRes=new JLabel();
	
	//Buttons
	JButton eval= new JButton("Evaluate");
	JButton save= new JButton("Save");
	JButton load= new JButton("Load");
	JButton diff=new JButton("Differenciate");
	JButton graph=new JButton("Draw Graph");
	
	
	//Textfields
	JTextField valueEq=new JTextField("",200);
	JTextField valueX=new JTextField("",5);
	JTextField valueY=new JTextField("",5);
	JTextField displayRes=new JTextField("",200);
	
	//Dimensions
	Dimension dimpanel=new Dimension(400,400);
	Dimension dimeq=new Dimension(400,100);
	Dimension dimvar=new Dimension(50,25);

	public Window(int width, int height) {
		
		equation.setText("Enter your equation :");
		setX.setText("Value for X:");
		setY.setText("Value for Y:");
		seeRes.setText("Result :");
		
		
		
		//Window Conf.
		window.setTitle("Formulator");
		window.setSize(width,height);
		window.setContentPane(frame);
		
		
		//Size settings
		frame.setPreferredSize(dimpanel);
		panelX.setPreferredSize(dimvar);
		panelY.setPreferredSize(dimvar);
		panelEq.setPreferredSize(dimeq);
		
		//Frame Layout
		frame.setLayout(new GridLayout(5,5));
		frame.add(panelEq);
		frame.add(panelX);
		frame.add(panelY);
		frame.add(panelButton);
		frame.add(panelResult);
		
		
		//Panels Layout
		panelEq.setLayout(new GridLayout(1,2));
		panelEq.add(equation);
		panelEq.add(valueEq);
		
		panelX.setLayout(new GridLayout(1,2));
		panelX.add(setX);
		panelX.add(valueX);
		
		panelY.setLayout(new GridLayout(1,2));
		panelY.add(setY);
		panelY.add(valueY);
		
		panelResult.setLayout(new GridLayout(1,2));
		panelResult.add(seeRes);
		panelResult.add(displayRes);
		
		panelButton.setLayout(new GridLayout(1,5));
		panelButton.add(eval);
		panelButton.add(graph);
		panelButton.add(diff);
		panelButton.add(save);
		panelButton.add(load);
		
		
		
		//ActionListeners
		eval.addActionListener(new EvalListener());
		graph.addActionListener(new GraphListener());
		diff.addActionListener(new DiffListener());
		save.addActionListener(new SaveListener());
		load.addActionListener(new LoadListener());
		
		//Close Op & Visibility
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		
		
		
	}
	class EvalListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String strEq= valueEq.getText();
			String strX=valueX.getText();
			String strY=valueY.getText();
			
			
			
			if(strEq.compareTo("")==0){
				displayRes.setText("Please Enter a formula");
			}else {
				try{
					
				FormulaElement form=FormulaElement.parseFormula(strEq);
				
					if(strX.compareTo("")==0 && strY.compareTo("")==0){
						displayRes.setText("Variables undefined");
					}
					else if(strX.compareTo("")==0) {
						Double valueOfY=Double.valueOf(strY);
						form.setVariableValue("X",0);
						form.setVariableValue("Y",valueOfY);
						displayRes.setText("Variable X undefined set to 0, Result :"+form.evaluate());
				
					}else if(strY.compareTo("")==0) {
						Double valueOfX=Double.valueOf(strX);
						form.setVariableValue("Y",0);
						form.setVariableValue("X",valueOfX);
						displayRes.setText("Variable Y undefined set to 0, Result :"+form.evaluate());
			
					}else{
						Double valueOfX=Double.valueOf(strX);
						Double valueOfY=Double.valueOf(strY);
						form.setVariableValue("X",valueOfX);
						form.setVariableValue("Y",valueOfY);
						displayRes.setText(""+form.evaluate());
					}
				}catch(ArrayIndexOutOfBoundsException err) {
					displayRes.setText("Error in equation parsing, check balance of paranthesis or operators");
				}
			
			}
			
			
			
			
		}
	}
		
	
	class DiffListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String strEq= valueEq.getText();
			String strX=valueX.getText();
			String strY=valueY.getText();
			
			
			
			if(strEq.compareTo("")==0){
				displayRes.setText("Please Enter a formula");
			}else {
				try{
				FormulaElement form=FormulaElement.parseFormula(strEq);
				
					if(strX.compareTo("")==0 && strY.compareTo("")==0){
						displayRes.setText("Variables undefined");
					}
					else if(strX.compareTo("")==0) {
						form.setVariableValue("X",0);
						displayRes.setText("Variable X undefined set to 0. Result :"+form.evaluate() + "\n Derivative: " +form.derivate());
				
					}else if(strY.compareTo("")==0) {
						form.setVariableValue("Y",0);
						displayRes.setText("Variable Y undefined set to 0. Result :"+form.evaluate() + "\n Derivative: " +form.derivate());
			
					}else{
						Double valueOfX=Double.valueOf(strX);
						Double valueOfY=Double.valueOf(strY);
						form.setVariableValue("X",valueOfX);
						form.setVariableValue("Y",valueOfY);
						displayRes.setText(""+form.evaluate()+"\n"+form.derivate());
					}
				}catch(ArrayIndexOutOfBoundsException err) {
					displayRes.setText("Error in equation parsing, check balance of paranthesis or operators");
				}
			
			
			}
		}
		
	}
	
	class GraphListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			GraphWindow(400,400);
		}
		
	}
	
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Calculator.save();
		}
		
	}
	
	class LoadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Calculator.save();
		}
		
	}
	
	public void GraphWindow(int width,int height) {
		JFrame window = new JFrame();
		
		
				
		//Panels
		JPanel graphFrame=new JPanel();
		JPanel boundariesX=new JPanel();
		JPanel boundariesY=new JPanel();
		JPanel step=new JPanel();
		
		//Labels
		JLabel Xfrom=new JLabel();
		Xfrom.setText("X from :");
		JLabel Yfrom=new JLabel();
		Yfrom.setText("Y from :");
		JLabel Xto=new JLabel();
		Xto.setText("To :");
		JLabel Yto=new JLabel();
		Yto.setText("To :");
		JLabel stepLabel=new JLabel();
		stepLabel.setText("Step size :");
		
		//Textfields
		JTextField Xmin=new JTextField();
		JTextField Xmax=new JTextField();
		JTextField Ymin=new JTextField();
		JTextField Ymax=new JTextField();
		JTextField stepSize=new JTextField();
		
		//Buttons
		JButton draw=new JButton("Draw");
		
		//Window Conf.
		window.setTitle("Graph Drawer");
		window.setSize(width,height);
		window.setContentPane(graphFrame);
		
		//Layouts
		graphFrame.setLayout(new GridLayout(4,1));
		graphFrame.add(boundariesX);
		graphFrame.add(boundariesY);
		graphFrame.add(step);
		graphFrame.add(draw);
		
		boundariesX.setLayout(new GridLayout(1,4));
		boundariesX.add(Xfrom);
		boundariesX.add(Xmin);
		boundariesX.add(Xto);
		boundariesX.add(Xmax);
		
		boundariesY.setLayout(new GridLayout(1,4));
		boundariesY.add(Yfrom);
		boundariesY.add(Ymin);
		boundariesY.add(Yto);
		boundariesY.add(Ymax);
		
		step.setLayout(new GridLayout(1,2));
		step.add(stepLabel);
		step.add(stepSize);
		
		//Close Op & Visibility
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		
	}
	
}


