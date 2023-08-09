package Multithreading;

// thread AA,BB,CC are executing at same time
// by extends Thread class we have created threads
class AA extends Thread{
	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println("Thread1");
			try {
				Thread.sleep(100);
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
	}
}

class BB extends Thread{
	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println("Thread2");
			try {
				Thread.sleep(100);
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
	}
}
// created a thread using runnable interface
class CC implements Runnable{
	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println("Thread3");
			try {
				Thread.sleep(100);
			}
			catch(Exception e) {
				System.out.println(e);
			}
			
		}
	}
}


public class Q2_threads {
	public static void main(String[] args){
		
		AA obj1 = new AA();
		BB obj2 = new BB();
		CC obj3 = new CC();
		
		obj1.start();
		obj2.start();
		Thread t1 = new Thread(obj3);
		t1.start();
		

		
		
	}

}
