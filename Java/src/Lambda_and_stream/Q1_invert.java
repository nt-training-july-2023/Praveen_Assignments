package Lambda_and_stream;
import java.io.*;
import java.util.*;


public class Q1_invert {
	public static void main(String[] args) throws Exception{
		try {
			FileReader fr = new FileReader("C:\\Users\\Praveen\\eclipse-workspace\\Training\\src\\Lambda_and_stream\\name");
			BufferedReader reader = new BufferedReader(fr);
			String originalData;
			ArrayList<String> a = new ArrayList<String>();
			while((originalData = reader.readLine())!=null)
			{
				a.add(originalData);
			}
			Collections.reverse(a);
			FileWriter fw = new FileWriter("C:\\Users\\Praveen\\eclipse-workspace\\Training\\src\\Lambda_and_stream\\writename");
			BufferedWriter bw = new BufferedWriter(fw);
			for(String s:a)
			{
				bw.write(s);
				bw.newLine();
			}
			bw.close();
			reader.close();
			System.out.println("inverted");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
