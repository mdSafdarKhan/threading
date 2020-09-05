package count_down_latch;

import java.util.concurrent.CountDownLatch;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl = new CountDownLatch(2);
		
		new Worker1(cdl);
		new Worker2(cdl);
		
		cdl.await();
		
		System.out.println("DB and File operations done!");
	}
}
