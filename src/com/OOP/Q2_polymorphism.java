package com.OOP;

class addition{
	
//	int a ,b,c;
//	addition(int a, int b,int c){
//		this.a= a;
//		this.b=b;
//		this.c=c;
//		
//	}
	
	int add(int a, int b) {
//		System.out.println("a+b = "+ a+b);
	return a+b;
	}
//	overloading by changing number of arguments
	void add(int a, int b, int c) {
		System.out.print("a+b+c = "+ (a+b+c));
		}
//	overloading by changing data type
	double add(double a, double b) {
	 return a+b;
 }
}

public class Q2_polymorphism {
	public static void main(String[] args) { 
		addition obj = new addition();
		System.out.println(obj.add(5,7));
		System.out.println(obj.add(5.5,7));
		obj.add(5,7,2);
		
	}
	
	

}
