package Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


//static Scanner input = new Scanner(System.in);




public class maps {
	Scanner input = new Scanner(System.in);
		public  HashMap<Integer,String> add(){
//			Scanner input = new Scanner(System.in);
			System.out.println("Enter number of pairs you want add in the map");
			int n=input.nextInt();
			
			
			HashMap<Integer,String> map = new HashMap<Integer,String>();
			int key;
			for(int i=0;i<n;i++) {
				System.out.println("Enter the key: Employee ID");
				key = input.nextInt();
				input.nextLine();
				System.out.println("Enter the value: Employee Name");
			   String value = input.next();
				map.put(key,value);
				}
			  
			  return map;
			
			}
		public void printele(HashMap<Integer,String> map) {
			
//			a =input.nextInt();
			for(Map.Entry<Integer,String> m: map.entrySet()) {
				
				Integer key = m.getKey();
				String value = m.getValue();
				System.out.println("Key:"+key+"Value:"+value);
				
			}

			
		}
		public static void main(String[] args) {
//			Scanner input = new Scanner(System.in);
			int a;
		
			maps obj = new maps();
		    System.out.println(obj.add());
		
		
	}

}
