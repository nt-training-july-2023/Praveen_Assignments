package Maps;

import java.util.HashMap;

public class Maps_CRUD {
	public static void Hashmap() {
		HashMap<Integer,String> hm = new HashMap<Integer,String>();
//		ADD
		hm.put(1,"Kodavali");
		hm.put(2, "Mohan");
		hm.put(3, "Venkata");
		hm.put(4, "Praveen");
		System.out.println("HashMap: "+ hm);
//		UPDATE
		hm.replace(1, "surname");
		hm.replace(4,"Praveen","Name");
		System.out.println("HashMap after updation : "+ hm);
//		REMOVE
		hm.remove(2);
		hm.remove(3,"Venkata");
		System.out.println("HashMap after removing the elements :"+ hm);
//		FETCH OR GET
		System.out.println(hm.get(4));
		System.out.println(hm.getOrDefault(4, null ));
		
	}
	
	public static void main(String[] args) {
		Maps_CRUD obj = new Maps_CRUD();
		obj.Hashmap();
		
	}

}
