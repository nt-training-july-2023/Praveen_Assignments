package Lambda_and_stream;

interface meth{
	String replace(String s);
}


public class Q4_lambda_exp {
	public static void main(String[] args) {
		String str = "lambda expression";
		  
		   
		   meth obj =(s)-> s.replaceAll("[AEIOUaeiou]", "#");
		   
		   String newstr = obj.replace(str);
		   System.out.println(newstr);
	}
		
	

}
