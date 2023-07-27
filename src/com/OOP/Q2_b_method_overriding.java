package com.OOP;

class parent{
	
	void bike(){
		System.out.println("honda");
	}
}
 class son2 extends parent1 {
	
}
class son1 extends parent1{
	
	void bike() {
		System.out.println("bmw");
	}
}

public class Q2_b_method_overriding {
	
	public static void main(String[] args) {
		son1 obj1 = new son1();
		son2 obj2 = new son2();
		obj1.bike();
		obj2.bike();
		
	}

}
