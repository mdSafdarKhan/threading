package producer_consumer;


public class Q {

	private int n;
	
	private boolean valueSet = false;
	
	public synchronized void produce(int i) throws InterruptedException {
		while(valueSet) {
			wait();
		}
		this.n = i;
		System.out.println("produced " + n);
		valueSet = true;
		notify();
	}
	
	public synchronized void consume() throws InterruptedException {
		while(!valueSet) {
			wait();
		}
		System.out.println("consumed " + n);
		valueSet = false;
		notify();
	}
}
