package cyclic_barrier;

public class Takeoff implements Runnable{

	@Override
	public void run() {
		System.out.println("TAKING OFF..." + this);
	}

}
