package threading;

import java.util.concurrent.locks.ReentrantLock;

class Shared9{
	static int count = 0;
}
class Thread10 implements Runnable{
	
	ReentrantLock lock;
	String name;
	
	public Thread10(ReentrantLock lock, String name) {
		this.lock = lock;
		this.name = name;
		new Thread(this, name).start();
	}

	@Override
	public void run() {
		System.out.println("starting " + name);
		try {
			//First, lock count.
			System.out.println(name + " is waiting to lock count");
			
			lock.lock();
			System.out.println(name + " is locking count");
			
			Shared.count++;
			System.out.println(name + "Shared.count");
			
			// Now, allow a context switch -- if possible.
			System.out.println(name + " is sleeping.");
			Thread.sleep(1000);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//Unlock
			System.out.println(name + " is unlocking count.");
			lock.unlock();
		}
	}
}
public class Threading20_LockDemo {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		
		new Thread10(lock, "A");
		new Thread10(lock, "B");
	}
}
