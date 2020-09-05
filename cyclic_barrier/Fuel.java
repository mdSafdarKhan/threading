package cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Fuel implements Runnable {

	CyclicBarrier cb;

	public Fuel(CyclicBarrier cb) {
		this.cb = cb;
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("FUEL CHECKED!");
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
