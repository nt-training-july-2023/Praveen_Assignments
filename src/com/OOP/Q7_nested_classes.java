package com.OOP;

//local inner class
// paremt class->method->inner class,inner class object creation inside the method

 class outer {
	  void outer_method() {
		 System.out.println("this is private outer class method");
	class inner{
		void m() {
			System.out.println("Inner class");
		}
		
		}
	inner obj1 = new inner();
	obj1.m();
	}
}

public class Q7_nested_classes {
	
	 public static void main(String[] args) {
		 
// local inner class object declaration		 
		 outer obj = new outer(); 
		 obj.outer_method();

//		 member inner class object declaration
		 out obj1 = new out();
		 out.in obj2 = obj1.new in();
		 obj2.method();
		 
	 }
	}
//member inner class

class out{
	class in{
		void method() {
			System.out.println("inner");
		}
		
	}
}


