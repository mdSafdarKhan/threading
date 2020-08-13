package threading;

import java.util.concurrent.Semaphore;

//A shared resource.
class Shared {
	static int count = 0;
}

//A thread of execution that increments count.
class IncThread implements Runnable{
	
	String name;
	Semaphore sem;
	
	public IncThread(Semaphore sem, String name) {
		this.name = name;
		this.sem = sem;
		new Thread(this, name).start();
	}

	@Override
	public void run() {
		System.out.println("Starting " + name);
		
		try {
			//First, get a permit
			System.out.println(name + " is waiting for a permit.");
			
			sem.acquire();
			System.out.println(name + " gets a permit.");
			
			// Now, access shared resource.
			for(int i=0; i<10; i++) {
				Shared.count++;
				System.out.println(name + ": " + Shared.count);
				
				// Now, allow a context switch -- if possible.
				Thread.sleep(10);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Release the permit.
		System.out.println(name + " releases the permit");
		sem.release();
	}
}

//A thread of execution that decrements count.
class DecThread implements Runnable{
	
	String name;
	Semaphore sem;
	
	public DecThread(Semaphore sem, String name) {
		this.name = name;
		this.sem = sem;
		new Thread(this, name).start();
	}

	@Override
	public void run() {
		
		System.out.println("Starting " + name);
		
		try {
			// First, get a permit.
			System.out.println(name + " waiting for permit");
			
			sem.acquire();
			System.out.println(name + " gets a permit");
			
			// Now, access shared resource.
			for(int i=0; i<10; i++) {
				Shared.count--;
				System.out.println(name + ": " + Shared.count);
				
				// Now, allow a context switch -- if possible.
				Thread.sleep(10);
			}
			
			// Release the permit.
			System.out.println(name + " releases the permit");
			sem.release();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class Threading14_SemDemo {

	public static void main(String[] args) {
		Semaphore sem = new Semaphore(1);
		new IncThread(sem, "A");
		new DecThread(sem, "B");
	}
}
