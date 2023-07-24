package com.loops;
import java.util.*;

public class sum_of_first_n_natural_numbers {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("enter the number");
		
		int n = input.nextInt();
		int i =0;
		int sum =0;
		while(i < n+1) {
			 sum = sum + i;
			 i++;
			
		}
		
		System.out.println("sum of first "+n+" natural numbers is "+ sum);
	}

}
