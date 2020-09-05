package count_down_latch;

import java.util.concurrent.CountDownLatch;

public class Worker1 implements Runnable{

	CountDownLatch cdl;
		
	Worker1(CountDownLatch cdl){
		this.cdl=cdl;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " performing DB operations");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("DB operations done in 5 sec.");
		cdl.countDown();
	}

}
