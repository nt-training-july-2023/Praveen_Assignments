package com.loops;
import java.util.*;

public class multiplication_table {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("enter the number");
		int n = input.nextInt();
		
		for(int i=0;i<11;i++) {
			System.out.println(n+ "*" +i+ "= "+ i*n);
		}
		
	}

}
