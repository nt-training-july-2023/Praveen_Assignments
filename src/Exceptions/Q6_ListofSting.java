package Exceptions;
import java.util.*;

public class Q6_ListofSting {
	public static void  main(String[] args) {
		List<String>  lis = new ArrayList<>(Arrays.asList("one","two",null,"three"));
		Scanner input = new Scanner(System.in);
		System.out.println("enter the index");
		int index = input.nextInt();		
		try {

			 if (lis.get(index) == null) {
				 throw new NullPointerException("Null value");
				 
			 }
			 else {
				 System.out.println(lis.get(index));
				 }
			}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e);
			System.out.println("Index is out of the range of list");
		}
		catch(NullPointerException e) {
			System.out.println(e);
			System.out.println("Element at index "+index+"is Null");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

}


