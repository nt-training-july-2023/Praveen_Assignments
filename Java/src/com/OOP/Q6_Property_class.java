package com.OOP;
import java.io.*;
import java.util.*;

public class Q6_Property_class {
	public static void main(String[] args) throws Exception {
		FileReader reader = new FileReader("C:\\Users\\Praveen\\eclipse-workspace\\Training\\src\\com\\OOP\\praveen.properties");
				Properties p = new Properties();
			    p.load(reader);  
			      
			    System.out.println(p.getProperty("user"));  
			    System.out.println(p.getProperty("password"));
	
	}

}
