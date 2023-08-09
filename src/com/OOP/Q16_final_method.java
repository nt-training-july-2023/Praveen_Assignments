package com.OOP;
// if we create final method we can't override the method 
class one{
	final void method() {
		System.out.println("We can't change ");
	}	
}
class two extends one{
//	Cannot override the final method from one
//	void method() {
//		
//	}
}

// if we create final class we cannot inherit or implement the class

final class three{
	
}

//class four extends three{
////	The type four cannot subclass the final class three
//}

public class Q16_final_method {
	

}
