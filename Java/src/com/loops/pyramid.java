package com.loops;
import java.util.*;

public class pyramid {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("enter the size of pyramid");
		int n= input.nextInt();
		for(int i=1;i<n+1;i++) {
			for(int y=1;y<i+1;y++) {
				System.out.print(i+" ");
			}
			System.out.println("");
		}
	}
	

}
