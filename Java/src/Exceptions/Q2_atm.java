package Exceptions;

import java.io.*;
import java.util.*;

class InvalidInputException extends Throwable{
	public InvalidInputException(String str) {
		super(str);
	}
}



public class Q2_atm {
	public static void main(String[] args) {
		
		System.out.println("enter the amount to deposit");
		Scanner input = new Scanner(System.in);
		int deposit = input.nextInt();
		System.out.println("enter the amount to withdraw");
		int withdraw = input.nextInt();
		
		try{
			if(withdraw>deposit ) {
			throw new InvalidInputException("Invalid input");
			}
			else if (withdraw==0) {
				throw new InvalidInputException("Invalid input");
			}
			else {
				System.out.println("Account balance remaining is "+ (deposit-withdraw));
			}
		   }
		catch(InvalidInputException e) {
			System.out.println(e);
		}
			
	}


}
