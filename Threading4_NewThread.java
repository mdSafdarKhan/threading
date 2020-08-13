package threading;

public class Threading4_NewThread implements Runnable{

	Thread t;
	
	public Threading4_NewThread() {
		Thread t = new Thread(this, "New Thread");
		System.out.println(t.getName() + " thread created.");
		t.start();
	}
	
	public Threading4_NewThread(String threadName){
		t = new Thread(this, threadName);
		System.out.println(t.getName() + " thread created.");
		t.start();
	}
	
	@Override
	public void run() {
		try {
			for(int i=5; i>0; i--) {
				System.out.println(t.getName() + " Thread: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println("Child thread interrupted");
		}
		System.out.println("Exiting child thread: " + t.getName());
	}
	
}
