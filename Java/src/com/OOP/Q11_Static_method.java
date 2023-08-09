package com.OOP;

class info{
	String name;
	static String Occupation = "Student";
	
	static void change() {
		Occupation = "employee";
//		Cannot make a static reference to the non-static field name
//		name = "praveen";
	}
	
	info(String name){
		this.name=name;
	}
	void information() {
		System.out.println(name+" is a "+Occupation);
	}
			
}

public class Q11_Static_method {
	
	public static void main(String[] args) {
		
		info obj = new info("praveen");
		obj.change();
		obj.information();
		
	}

}
