package threading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//An example of CyclicBarrier.
class MyThread2 implements Runnable{
	
	CyclicBarrier cb;
	String name;
	
	public MyThread2(CyclicBarrier cb, String name) {
		this.cb = cb;
		this.name = name;
		new Thread(this, name).start();
	}

	@Override
	public void run() {
		System.out.println(name);
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}

//An object of this class is called when the CyclicBarrier ends.
class BarAction implements Runnable{

	@Override
	public void run() {
		System.out.println("barrier reached");
	}
	
}

public class Threading16_BarDemo {
	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
		
		System.out.println("starting");
		
		new MyThread2(cb, "A");
		new MyThread2(cb, "B");
		new MyThread2(cb, "C");
//		new MyThread2(cb, "X");
//		new MyThread2(cb, "Y");
//		new MyThread2(cb, "Z");
	}
}
