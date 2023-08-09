package com.array;
import java.util.*;

public class largest_num_in_array {
	public static void main(String[] args) {
//		int intAa[];
//		intAa = new int[10];
//		System.out.println(intAa);
		int[] arr = new int[10];
		System.out.println(arr);
		
		Scanner input = new Scanner(System.in);
		int n =0;
		System.out.println("Enter the numbers");
		for(int i=0; i<10;i++) {
			n =input.nextInt();
			arr[i]=n;
		}
		int L=0;
		for(int i=0; i<arr.length;i++) {
			if(arr[i]>L) {
				L=arr[i];
			}
			
		}
		System.out.println("largest number the given array is :"+L);
		
	}

}
