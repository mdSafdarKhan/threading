package count_down_latch;

import java.util.concurrent.CountDownLatch;

public class Worker2 implements Runnable{

	CountDownLatch cdl;
		
	Worker2(CountDownLatch cdl){
		this.cdl=cdl;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " performing File operations");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("File operations done in 10 sec.");
		cdl.countDown();
	}

}
