package threading;

public class Threading6_MultiThreadDemo {
	public static void main(String[] args) {
		new Threading4_NewThread("One");
		new Threading4_NewThread("Two");
		new Threading4_NewThread("Three");
		
		try {
			for(int i=5; i>0; i--) {
				System.out.println("Main Thread: " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted");
		}
		
		System.out.println("Main thread exited...");
	}
}
