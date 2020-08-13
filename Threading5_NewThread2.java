package threading;

public class Threading5_NewThread2 extends Thread{
	
	public Threading5_NewThread2() {
		super("Demo Thread");
		System.out.println("Child thread: " + this);
		start();
	}
	
	@Override
	public void run() {
		try {
			for(int i=5; i>0; i--) {
				System.out.println("Child Thread: " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println("Child thread interrupted");
		}
		System.out.println("Exiting child thread...");
	}

}
