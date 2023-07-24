package com.array;

public class add_2d_array {
	
	static void print(int a[][],int rs,int cs) {
		
		for(int i=0;i<rs;i++) {
			for(int j=0; j<cs; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	static int[][] add(int a[][],int b[][] ,int s){
		int  C[][]= new int[s][s];
		for(int i=0;i<s;i++) {
			for(int j=0;j<s;j++) {
				 C[i][j]=a[i][j]+b[i][j];
			}
		}
		return C;
		
	}
	
	public static void main(String[] args) {
		
		int s=3;
		
		int A[][]= {{1,1,1},{1,1,1,},{1,1,1}};
		
		int B[][]= {{2,2,2},{2,2,2},{2,2,2}};
		
		System.out.println("Matrix A");
		print( A,s,s);
		System.out.println("Matrix B");
		print(B,s,s);
		
		int C[][]= add(A,B,s);
		System.out.println("Matrix C");
		print(C,s,s);
		
		
		
		
		
		
		

			
		
	
	}
}
