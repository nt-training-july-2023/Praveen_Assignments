package Lambda_and_stream;
import java.io.*;

public class Q2_listofDirectories {
	public static void main(String[] args) {
		File f = new File("C:\\Users\\Praveen\\eclipse-workspace\\Training\\src\\Lambda_and_stream");
		String[] getstr = f.list();
		
		for(String s : getstr) {
			System.out.println(s);
		}
	}

}
