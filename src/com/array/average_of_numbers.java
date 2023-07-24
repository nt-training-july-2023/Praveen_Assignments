package com.array;
import java.util.*;


public class average_of_numbers {
	public static void main(String[] args) {
		int[] arr = new int[10];
		System.out.println("enter the numbers");
		Scanner input = new Scanner(System.in);
		for(int i=0;i<10;i++) {
			int num=input.nextInt();
			arr[i]= num;
		}
		int total=0;
		for(int i=0;i<arr.length;i++) {
			 total=arr[i]+total;
		}
		float t = total;
		float avg=0;
		avg=t/arr.length;
		System.out.println("average of nums in the array :"+ avg);
	}

}
