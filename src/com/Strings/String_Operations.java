package com.Strings;

public class String_Operations {
	
	static void length(String str) {
		
		System.out.println("length of the string is :"+ str.length());
		
	}
	
	static void concatenation(String str, String str2) {
		
	    System.out.println(str.concat(" "+str2));
	}
	
	static char get_char(String str, int p) {
		char c = str.charAt(p);
		return c;
	}
	static void Replace(String str, char r) {
//		using range, using string builder , using string buffer
		
		int p=0;
		
		StringBuilder Stri = new StringBuilder(str);
		Stri.setCharAt(p, r);
		System.out.println("new string : "+ Stri );
	}
	public static void main(String[] args) {
		
	    String str = "praveen";

		length(str);
		
		String str2 = "Kodavali";
		
		concatenation(str,str2);
		
		int p=3;
		
		System.out.println(get_char(str,p));
		
		char r ='P' ;
		
		Replace(str,r);
		
		
		
	}
}
