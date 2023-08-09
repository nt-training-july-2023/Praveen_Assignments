package com.OOP;

abstract class bank{
	abstract void loan_offer();
}

class sbi extends bank{
	
	void loan_offer() {
		System.out.println("sbi offers 75% of fee");
	}
}	
class bob extends bank{
	 
	void loan_offer() {
		System.out.println("bob offers 90% of the fee");
		}
	
}
public class Q5_abstract_class {
	
	public static void main(String[] args) {
		
		bank obj1= new sbi();
		bank obj2= new bob();
		obj1.loan_offer();
		obj2.loan_offer();
		
	}
	
	

}
