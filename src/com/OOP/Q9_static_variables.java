package com.OOP;

class employee1{
	String name;
	int id;
	static String company="Nucleusteq";
	
	employee1(String name, int id){
		this.name = name;
		this.id = id;
	
	}
	
	void details() {
		System.out.println(name +" is a emloyee of "+ company+" and his employee id is "+ id);
	}
}

public class Q9_static_variables {
	public static void main(String[] args) {
		employee1 obj = new employee1("praveen",123);
		employee1 obj1 = new employee1("kodavali",123);
		
		obj.details();
		obj1.details();
	}

}
