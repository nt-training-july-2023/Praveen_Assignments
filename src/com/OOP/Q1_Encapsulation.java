package com.OOP;

class employee{
	
	private String name;
	int age;
	float height;
	private int salary;
//constructor	
//	employee(){
//		String n = "Employee name";
//		System.out.println(n);
//	}
	
	public String getname() {
		return name;
	}
//	public method
	public void setname(String name) {
		this.name=name;
	}
	
	public int getage() {
		return age;
	}
//	default method
	 void setage(int age) {
		this.age = age;
	}
	 
	 public float getheight() {
		 return height;
	 }
//	protected method
	 protected void setheight(float height) {
		 this.height = height;
	 }
	 
	 public int getsalary() {
		 return salary;
	 }
//	 private method 
	 
	 private void setsalary(int salary) {
		 this.salary=salary;
	 }
	 
	  
	
}
public class Q1_Encapsulation {
	public static void main(String[] args) {
		employee obj = new employee();
		obj.setname("praveen");
//		System.out.println(obj.name);
//		we cannot call the string name if it is private
		System.out.println("employee name is "+obj.getname());
		obj.setage(21);
		System.out.println(obj.getname()+" age is "+obj.getage());
		obj.setheight(6.0f);
// age is public variable we can access that 
		System.out.println(obj.getname()+" height is"+obj.getheight()+" and age is "+ obj.age);
//		obj.setsalary(000);
//		System.out.println(obj.getname()+" monthly income is "+ obj.getsalary());
		System.out.println("private method getsalary is not accessible");

		
	}

}
