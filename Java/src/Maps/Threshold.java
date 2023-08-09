package Maps;

import java.util.HashMap;
import java.util.Scanner;


public class Threshold {
//	Scanner input = new Scanner(System.in);
	
	static void add(HashMap<Integer,String> hm) {
		hm.put(1,"Kodavali");
		hm.put(2, "Mohan");
		hm.put(3, "Venkata");
		hm.put(4, "Praveen");
	}
	static void print(HashMap<Integer,String> hm) {
		System.out.println(hm);
	}
	static void threshold(HashMap<Integer, String> hm) {
		Scanner input = new Scanner(System.in);
		int s=hm.size();
		System.out.println("Enter the threshold value");
		int t = input.nextInt();
		if (t>=s) {
			System.out.println("Entered if condition");
			hm.clear();
		}
		System.out.println("After updation :"+ hm);
	}
	
	static void check(HashMap<Integer, String> hm) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the key you want search");
		int f= input.nextInt();
		System.out.println(hm.containsKey(f));
		
	}
	
	static void println(HashMap<Integer, String > hm) {
		System.out.println(hm.keySet());
		System.out.println(hm.values());
		
	}
	static void mapped(HashMap<Integer, String> hm ) {
		System.out.println("Before :"+hm);
		hm.remove(1, "Kodavali");
		System.out.println("After  :"+hm);
		
	}
	
	
	public static void main(String[] args) {
		
		HashMap<Integer,String> hm = new HashMap<>();
		add(hm);
		print(hm);
		threshold(hm);
		check(hm);
		println(hm);
		mapped(hm);
//		System.out.println(hm);
	}

}
