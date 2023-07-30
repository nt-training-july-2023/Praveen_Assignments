package Exceptions;
import java.io.*;

public class Q4_file_reading {
	public static void main(String[] args)throws Exception {
		try {
			
		FileReader input = new FileReader("C:\\Users\\Praveen\\eclipse-workspace\\Training\\src\\Exceptions\\TXT");
		int i =0;
			while((i=input.read())!=-1) {
				System.out.println((char)i);
			}
			input.close();
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("success");
		}
		
		
	}

}
