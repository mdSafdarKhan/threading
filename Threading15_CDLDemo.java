package threading;

import java.util.concurrent.CountDownLatch;

class MyThread implements Runnable{
	
	CountDownLatch cdl;
	
	public MyThread(CountDownLatch cdl) {
		this.cdl = cdl;
		new Thread(this).start();
	}

	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println(i);
			cdl.countDown();
		}
	}
}

public class Threading15_CDLDemo {
	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(5);
		
		System.out.println("starting");
		
		new MyThread(cdl);
		
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}
}
