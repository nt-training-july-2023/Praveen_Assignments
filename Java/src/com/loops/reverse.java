package com.loops;
import java.util.*;

public class reverse {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number to reverse");
		
		String n = input.next();
		String reverse="";
		
		for(int i=0; i<n.length();i++) {
			reverse = n.charAt(i)+ reverse;
//			System.out.println(n.charAt(i));
			}
		System.out.println("reverse of the number is " + reverse);
//		int num = Integer.parseInt(reverse);
//		System.out.println(num.getClass());
		
	}

}
