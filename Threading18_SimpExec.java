package threading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyThread4 implements Runnable{
	
	CountDownLatch cdl;
	String name;
	
	MyThread4(CountDownLatch cdl, String name){
		this.cdl = cdl;
		this.name = name;
		new Thread(this, name);
	}

	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println(name + ": " + i);
			cdl.countDown();
		}
	}
}

//A simple example that uses an Executor.
public class Threading18_SimpExec {
	public static void main(String[] args) {
		CountDownLatch cd1 = new CountDownLatch(5);
		CountDownLatch cd2 = new CountDownLatch(5);
		CountDownLatch cd3 = new CountDownLatch(5);
		CountDownLatch cd4 = new CountDownLatch(5);
		CountDownLatch cd5 = new CountDownLatch(5);
		
		ExecutorService es = Executors.newFixedThreadPool(2);
		
		System.out.println("starting");
		
		// Start the threads.
		es.execute(new MyThread4(cd1, "A"));
		es.execute(new MyThread4(cd2, "B"));
		es.execute(new MyThread4(cd3, "C"));
		es.execute(new MyThread4(cd4, "D"));
		es.execute(new MyThread4(cd5, "E"));
		
		try {
			cd1.await();
			cd2.await();
			cd3.await();
			cd4.await();
			cd5.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		es.shutdown();
		System.out.println("done");
	}
}
