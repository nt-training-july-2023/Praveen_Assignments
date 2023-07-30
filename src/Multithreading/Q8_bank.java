package Multithreading;
import java.util.*;

class balance{
	int B,W,D;
	balance(int W,int D){
		this.W=W;
		this.D=D;
		}
	public void increment() {
		B=B+D;
		System.out.println(B);
	}
	public void decrement() {
		B=B-W;
		System.out.println(B);
	}
}

public class Q8_bank {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		

		System.out.println("Deposit- enter the amount");
		int D = input.nextInt();
		
		System.out.println("withraw-enter the amount");
		int W = input.nextInt();
		
		
		balance obj = new balance(W, D);
		
		Runnable obj1=()->{
			obj.increment();
		};
		Runnable obj2=()->{
			obj.decrement();
		};
		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		t1.start();
		t2.start();
		
		
		
		
		
		
		
	}

}
