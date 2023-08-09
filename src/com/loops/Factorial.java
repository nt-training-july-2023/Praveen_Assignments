package com.loops;
import java.util.*;


public class Factorial {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("enter the number");
		int n = input.nextInt();
		int fact=1;
		int i = 1;
		for(i=1;i<n+1;i++) {
			fact = fact*i;
		}
		System.out.println("factorial of given number is "+ fact);
		
	}

}
