package Collections;
import java.util.*;

public class Integer_collection {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
//		Collection<Object> c = new Collection<>();
//		declaration
		List<Integer> lis= new ArrayList<>();
		List<Integer> li = new ArrayList<>(Arrays.asList(1,2,3,4,5));
//		adding elements in array list
//		lis.add(1);
//		lis.add(2);
// adding elements by user input
		System.out.println("Enter the nuumber of elements you want to enter in the list:");
		int count = input.nextInt();
		
		for(int i=0; i<count;i++) {
			System.out.println("enter the element at index"+i);
			int ele = input.nextInt();
			lis.add(ele);
		}
		System.out.println("Original lis "+lis);
		Collections.reverse(lis);
		System.out.println("Reversed lis"+lis);

// adding +5 to elements greater than 50
	    for (int i=0;i<count;i++) {
	    	if(lis.get(i)>50) {
	    		lis.set(i,lis.get(i)+5);	
	    	}
	    }
	    
	    System.out.println("After updation :"+ lis);
		
//	displaying elements less than 60
	    System.out.println("Elements less than 60");
	    for (int i=0;i<count;i++) {
	    	if(lis.get(i)<60) {
	    		System.out.print(lis.get(i)+" ");	
	    	}
	    }
	    
		
	}

}
