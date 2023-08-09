package com.OOP;

public abstract class Q10_Static_block {
	
	public static void main(String[] args) {
		System.out.println("main method called");
	}
	static {
		System.out.println("static block is used to initialize static data members "
				+ "And it is executed before main method");
	}

}
