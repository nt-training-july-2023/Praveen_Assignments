package com.praveen.empMan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	@Id
	private int emp_id;
	private String emp_name;
	private int emp_age;
	private String emp_city;
	private String emp_designation;
	
	
	public Employee(int emp_id, String emp_name, int emp_age, String emp_city, String emp_designation) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_age = emp_age;
		this.emp_city = emp_city;
		this.emp_designation = emp_designation;
	}
	


	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getEmp_id() {
		return emp_id;
	}


	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}


	public String getEmp_name() {
		return emp_name;
	}


	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}


	public int getEmp_age() {
		return emp_age;
	}


	public void setEmp_age(int emp_age) {
		this.emp_age = emp_age;
	}


	public String getEmp_city() {
		return emp_city;
	}


	public void setEmp_city(String emp_city) {
		this.emp_city = emp_city;
	}


	public String getEmp_designation() {
		return emp_designation;
	}


	public void setEmp_designation(String emp_designation) {
		this.emp_designation = emp_designation;
	}


	@Override
	public String toString() {
		return "Emp [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_age=" + emp_age + ", emp_city=" + emp_city
				+ ", emp_designation=" + emp_designation + "]";
	}
	
	
	
	

}
