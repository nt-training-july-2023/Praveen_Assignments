package com.loops;

import java.util.Scanner;

public class palindrome {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number");
		
		String n = input.next();
		String reverse="";
		
		for(int i=0; i<n.length();i++) {
			reverse = n.charAt(i)+reverse;

			}
		System.out.println(n+","+reverse);
		System.out.println(n.length()+","+reverse.length());
		if(n.equals(reverse)) {
			System.out.println("given number is palindrome");
			
		}
		else {
			System.out.println("given number is not a palindrome");
		}

		}
	}