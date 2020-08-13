package threading;

//Suspending and resuming a thread the modern way.
class PrintingMachine {

	boolean suspendFlag = false;
	
	public void print(String threadName) {
		try {
			for(int i=0; i<10; i++) {
				System.out.println(threadName + ": " + i);
				Thread.sleep(200);
				synchronized (this) {
					while(suspendFlag) {
						wait(); //only called through synchronized context
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized void mysuspend() {
		suspendFlag = true;
	}
	
	synchronized void myresume() {
		suspendFlag = false;
		notify();
	}
}

class CustomThread implements Runnable{
	
	PrintingMachine pm;
	String threadName;
	Thread t;
	
	public CustomThread(PrintingMachine p, String tName) {
		pm = p;
		threadName = tName;
		t = new Thread(this, threadName);
		t.start();
	}
	
	@Override
	public void run() {
		pm.print(threadName);
	}
	
}

public class Threading13_SuspendResume {
	public static void main(String[] args) {
		PrintingMachine p = new PrintingMachine();
		CustomThread t1 = new CustomThread(p, "thread-1");
		CustomThread t2 = new CustomThread(p, "thread-2");
		
		try {
			Thread.sleep(1000);
			t1.pm.mysuspend();
			System.out.println("suspending thread-1");
			
			Thread.sleep(1000);
			t1.pm.myresume();
			System.out.println("resuming thread-1");
			
			Thread.sleep(1000);
			t2.pm.mysuspend();
			System.out.println("suspending thread-2");
			
			Thread.sleep(1000);
			t2.pm.myresume();
			System.out.println("suspending thread-2");
			
			//Wait for thread to finish
			t1.t.join();
			t2.t.join();
			
		} catch (InterruptedException e) {
		}
	}
}
