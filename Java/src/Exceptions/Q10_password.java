package Exceptions;
import java.util.*;

class InvalidPasswordException extends Throwable{
	public InvalidPasswordException(String str) {
		
		super(str);
	}
}	

public class Q10_password {
	public static void validate(String password)throws InvalidPasswordException {
		
		if(password.length()<8) {
			throw new InvalidPasswordException("Password must contains atleast 8 characters");
		}
		
		boolean containletter = false;
		boolean containnumber = false;
		
		for(char ch: password.toCharArray()) {
			if (Character.isLetter(ch)) {
				containletter=true;
			}
			else if (Character.isDigit(ch)) {
				containnumber=true;
			}
			else {
				throw new InvalidPasswordException("Password must contain only letters and numbers");
			}
			}
		if(!(containnumber&&containletter)) {
			throw new InvalidPasswordException("password must cointain numbers and letters");
			
		}
  }

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println ("Enter the password");
		String password = input.next();
		try {
			validate(password);
			System.out.println("Password is validate");
		}
		catch(InvalidPasswordException e) {
			System.out.println(e);
		}
	}
}
	
 