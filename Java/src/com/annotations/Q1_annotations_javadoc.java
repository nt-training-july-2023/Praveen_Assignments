package com.annotations;
import java.math.*;

/**
 * For calculating the area we have created area class
 * @author Praveen
 * 
 */

class area{
/**
 * @param length it is the length of rectangle 
 * @param breadth it is the breadth of rectangle
 */

	 
	 void rec_area(int length,int breadth) {
		 System.out.println("area of rectangle is "+2*length*breadth) ;
	 }

	void tri_area(int height,int base) {
		 System.out.println("are of triangle is "+ (0.5)*(height)*(base) );
	 }
	 
	 void circle(int radius) {
		 System.out.println("area of circle is "+ Math.PI*radius*radius );
	 }
	 
	 void override_method() {
		 System.out.println("we want this mehtod to be overriden");
		 
	 }
}	 
class AArea extends area {
	
    @Override
	void override_method() {
		System.out.println("this method is overriden if not it will throw an error -The method override_metho() of type Area must override or implement a supertype method");
	}
}
public class Q1_annotations_javadoc {
	
	public static void main(String[] args) {
		area robj = new area();
		area tobj = new area();
		area cobj = new area();
		
		robj.rec_area(5, 8);
		tobj.tri_area(8, 4);
		cobj.circle(5);
		area obj = new AArea();
		obj.override_method();
	}
}	

