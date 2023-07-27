package com.OOP;

class O{
	void method() {
	}
}

public class Q8_anonymous_inner_class {
	public static void main(String[] args) {
		O obj = new O() { // anonymous class is created here
			void method() {
				System.out.println("Anonymous class");
			}
		};
		obj.method();
	}

}
