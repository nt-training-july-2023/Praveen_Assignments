package Exceptions;
import java.util.*;

class InvalidDimensionException extends Throwable{
	public InvalidDimensionException(String str) {
		super(str); 
	}
}

public class Q3_area_of_triangle {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the length of the triangle");
		int length = input.nextInt();
		System.out.println("Enter the breadth of the triangle");
		int breadth = input.nextInt();
		
		try {
			if(length >0 || breadth>0 ) {
				System.out.println("Area of the triangle :"+ ((0.5)*length*breadth));
			}
			else {
				throw new InvalidDimensionException("Length and Breadth should be >0");
			}
		}
		catch(InvalidDimensionException e) {
			System.out.println(e);
		}
		
	}

}
