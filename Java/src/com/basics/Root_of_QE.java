package com.basics;
import java.util.*;


public class Root_of_QE {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("For the QE ax^2+bx+c ");
		System.out.println("Enter the value of a:");
		int a = input.nextInt();
		System.out.println("enter the value of b:");
		int b = input.nextInt();
		System.out.println("Enter the value of c:");
		int c = input.nextInt();
		
		float D = (b*b) - (4*a*c);
//		If determinant is greater than 0 roots are [-b +squareroot(determinant)]/2*a and [-b -squareroot(determinant)]/2*a.
		if(D>0) {
			double root1= ((-b)+Math.sqrt(D))/ 2*a;
			double root2= ((-b)-Math.sqrt(D))/ 2*a;	
			System.out.println((int) root1+","+(int) root2 );
		}
//		If determinant is equal to 0 root value is (-b+Math.sqrt(d))/(2*a)
		
		else if(D==0) {
			double root = -b/2*a;
			System.out.println("roots of equation are "+ root +","+root);
			}
		else {
			System.out.println("no real roots");
		}
		
		System.out.println("")
;
		
	}

}
