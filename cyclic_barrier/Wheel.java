package cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Wheel implements Runnable {

	CyclicBarrier cb;

	Wheel(CyclicBarrier cb) {
		this.cb = cb;
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("WHEELS CHECKED!");
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
