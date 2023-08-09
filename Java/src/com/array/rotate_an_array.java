package com.array;
import java.util.*;


public class rotate_an_array {
	
	static void rotate(int arr[],int d, int n) {
		
		int[] temp = new int[n];
		
		int x=0;
		
		for(int i=d;i<n;i++) {
			temp[x]=arr[i];
			x++;
		}
		for (int i=0;i<d;i++) {
			temp[x]=arr[i];
			x++;
		}
		for(int i=0; i<n;i++) {
			arr[i]=temp[i];
		}
		
	}
	
	static void print(int arr[],int n) {
		for(int i=0;i<n;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	public static void main(String[] args) {
		int arr[] = {1,2,3,4,5};
		int d =2;
		int N = arr.length;
		
		rotate(arr ,d ,N);
		print(arr,N);
			
	}

}
