package com.OOP;

final class IOS{
	void OS(){
		System.out.println("IOS is only for apple");
		
	}
}

// final class can't be inherited or extended.
//class IOS_copy extends IOS{
//	
//	System.out.println("IOS can't be copied");
//	
//}

public class Q15_final_class {
	public static void main(String[] args) {
		
		IOS obj = new IOS();
		obj.OS();
		
	}

}
