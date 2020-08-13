package threading;

//A correct implementation of a producer and consumer.
class Q2 {

	int n;
	boolean valueSet = false;
	
	synchronized int get() {
		
		while(!valueSet) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Got2: " + n);
		
		valueSet = false;
		notify();
		return n;
	}
	
	synchronized void put(int n) {
		
		while(valueSet) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.n = n;
		valueSet = true;
		System.out.println("Put2: " + n);
		
		notify();
	}
	
}

class Producer2 implements Runnable{

	Q2 q;
	
	Producer2(Q2 q){
		this.q = q;
		new Thread(this, "Producer2").start();
	}
	
	@Override
	public void run() {
		
		int i=0;
		
		while(true) {
			q.put(i++);
		}
	}
}

class Consumer2 implements Runnable{

	Q2 q;
	
	public Consumer2(Q2 q) {
		this.q = q;
		new Thread(this, "Consumer2").start();
	}
	
	@Override
	public void run() {
		while(true) {
			q.get();
		}
	}
}

public class Threading11_PC2{
	public static void main(String[] args) {
		Q2 q = new Q2();
		new Producer2(q);
		new Consumer2(q);
		
		System.out.println("Press ctrl+c to stop.");
	}
}
