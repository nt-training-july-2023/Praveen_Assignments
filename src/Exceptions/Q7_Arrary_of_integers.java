package Exceptions;
import java.util.*;
import java.io.*;

public class Q7_Arrary_of_integers {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a[] = {1,2,3,4,5,6};
		System.out.println("enter the index ");
		int index = input.nextInt();
		
		try {
			if(index<a.length && index>-1) {
				System.out.println("element at the index "+index+"is "+ a[index]);
			}
			else {
				throw new IndexOutOfBoundsException("index is out of range");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
				
		
	}

}
