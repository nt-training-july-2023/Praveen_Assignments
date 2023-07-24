package com.basics;
import java.util.*;


public class Area_of_triangle {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the length");
		int length = input.nextInt();
		
		System.out.println("enter the breadth");
		float breadth = input.nextFloat();
		
		System.out.println("area of the traingle is "+ (length*breadth )*(0.5));
		
		
		
	}

}
