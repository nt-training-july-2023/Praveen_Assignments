package Exceptions;
import java.util.*;
import java.io.*;
import java.nio.CharBuffer;

public class Q8_file_reading {
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		System.out.println("enter the address of file");
		String fn = input.next();
		try {
			
//			C:\Users\Praveen\eclipse-workspace\Training\src\Exceptions\TXT
			FileReader fr = new FileReader(fn);
			int i;
			
			while((i=fr.read())!=-1) {
				System.out.print((char)i);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
	}

}
