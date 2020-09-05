package semaphore;

import java.util.concurrent.Semaphore;

public class Table {

	public void count(Semaphore sem, int n) {
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (int i = 1; i <= 10; i++) {
			System.out.println(i + " * " + n + " = " + (i * n));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sem.release();
	}

}
