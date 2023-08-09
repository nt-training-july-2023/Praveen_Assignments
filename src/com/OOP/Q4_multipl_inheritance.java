package com.OOP;

interface android{
	void os();
}

// multiple inheriatnce in java achieved by interfaces
class oneplus implements android {
	
	public void os() {
		System.out.println("oxygen os");
		
	}
}	
class iqoo implements android {
	public void os() {
		System.out.println("Funtouch os");
	}

}

public class Q4_multipl_inheritance {
	
	public static void main(String[] args) {
		oneplus obj1 = new oneplus();
		iqoo obj = new iqoo();
		obj.os();
		obj1.os();
	}

}
