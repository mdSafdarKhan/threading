package cyclic_barrier;

import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(2, new Takeoff());
		
		System.out.println("STARTING CHECKS");
		
		new Fuel(cb);
		new Wheel(cb);
	
	}

}
