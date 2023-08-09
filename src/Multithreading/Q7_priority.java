package Multithreading;

class F extends Thread{
	public void run() {
		for(int i=0;i<10;i++) {
		System.out.println("Thread F");
		try {
			Thread.sleep(100);
		}
		catch(Exception e ) {
			
		}
		}
	}
}

class S extends Thread{
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("Thread S");
			try {
				Thread.sleep(100);
			}
			catch(Exception e ) {
				
			}
			}
	}
}

public class Q7_priority {
	public static void main(String[] args) {
		F obj1= new F();
		S obj2 = new S();
		System.out.println(obj1.getPriority());
		
		obj1.setPriority(10);
		System.out.println(obj1.getPriority());
		
		obj1.start();
		obj2.start();
		

		
		
	}

}
