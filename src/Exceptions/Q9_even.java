package Exceptions;
import java.util.*;

class NotEvenNumberException extends Throwable{
	public NotEvenNumberException(String str) {
		super(str);
	}
}

public class Q9_even {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("enter the number");
		int num = input.nextInt();
		try{
			if(num%2==0) {
				System.out.println("Given number is even");
			}
			else {
				throw new NotEvenNumberException("Given number is not even");
				
			}
		}
		catch(NotEvenNumberException e){
//			System.out.println(num%2);
			System.out.println(e.getMessage());
		}
		
	}

}
