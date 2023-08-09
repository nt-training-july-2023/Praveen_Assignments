package com.OOP;
class normal_class{
	private int i=5;
	static private int y=6;
	static class static_class{
		void method() {
//			Cannot make a static reference to the non-static field i
//			System.out.println(i);
			
//		static class method can only access static variables of outer class	
			System.out.println(y);
		}
	}
	
}

public class Q12_static_class {
	public static void main(String[] args) {
		normal_class.static_class obje = new normal_class.static_class();
		obje.method();
		
		
	}

}
