package com.OOP;

class grand_parent{
	
	int cars =1;
	void bike() {
		System.out.println("cycle");
	}
}
//single inheritance

class parent1 extends grand_parent{
	void bike() {
		System.out.println("i have honda");
	}
}
// multilevel inheritance
class son_a extends parent1{
	void bike() {
		System.out.println("son_a have R15");
	}
	void car_() {
		System.out.println("i have "+ cars + " car");
	}
}
// hierarchical inheritance
class son_b extends parent1{
	
}


public class Q3_inheritance {
	public static void main(String[] args) {
		
		son_b s2 = new son_b();
		son_a s1 = new son_a();
		parent1 p = new parent1();
		s2.bike();
		s1.bike();
		s1.car_();
		p.bike();
		
	}

}
