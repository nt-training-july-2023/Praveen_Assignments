package Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class String_Collection {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		LinkedHashSet<String> set = new LinkedHashSet();
		System.out.println("Enter the number of strings you want to enter");
		int n = input.nextInt();
		String element;
		for(int i=0; i<n;i++) {
			System.out.println("Enter the elements at the index:"+i);
			element = input.next();
			set.add(element);
		}
		
//		LinkedHashSet will no allow duplicates and it show in the insertion order
		System.out.println("Set is "+set);
		
	}
	
 
	

}
