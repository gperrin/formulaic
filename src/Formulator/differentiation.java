/*
 * This file implements the numerical differentiation algorithm
 * As our project use symbolic differentiation, we don't use this file
 * But as we did it first, you can still see what we did
 */
package Formulator;

import java.util.ArrayList;

public class differentiation {

	/*public static void main(String[] args) {
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> y = new ArrayList<Double>();
		ArrayList<Double> answer = new ArrayList<Double>();
		ArrayList<Double> answer2 = new ArrayList<Double>();

		y.add(0.0);
		x.add(20.0);
		
		y.add(0.4);
		x.add(71.0);
		
		y.add(1.0);
		x.add(110.0);
		
		y.add(1.75);
		x.add(161.0);
		
		y.add(2.5);
		x.add(178.0);
		
		answer=derive(y,x);
		answer2=derive(y,answer);

		System.out.println(y);
		System.out.println(x);
		System.out.println("f' ; "+answer);
		System.out.println("f'' ; "+answer2);
	}*/

	@SuppressWarnings("unused")
	private static ArrayList<Double> derive(ArrayList<Double> y, ArrayList<Double> x) {
		ArrayList<Double> ans = new ArrayList<Double>();
		for (int i = 0; i < x.size(); i++) {
			if (i==0) {
				ans.add(forward(x.get(i) ,x.get(i+1),y.get(i) ,y.get(i+1)));
			}
			else if(i==x.size()-1){
				ans.add(backward(x.get(i) ,x.get(i-1),y.get(i) ,y.get(i-1)));
			}
			else if(i>0 && i<x.size()){		
					ans.add(central(x.get(i+1) ,x.get(i-1),y.get(i+1) ,y.get(i-1)));
			}
		}
		return ans;
		
	}

	public static Double forward(double xfirst,double xsecond,double yfirst, double ysecond) {
 		double total = (xsecond-xfirst)/(ysecond-yfirst) ;
		return total;
	}

	public static Double central(double xfirst,double xsecond,double yfirst, double ysecond) {
 		double total = (xfirst-xsecond)/(yfirst-ysecond) ;
		return total;
	}

	public static Double backward(double xfirst,double xsecond,double yfirst, double ysecond) {
 		double total = (xfirst-xsecond)/(yfirst-ysecond) ;
		return total;
	}

	

}
