package threading;

public class Threading3_ThreadDemo2 {
	public static void main(String[] args) {
		new Threading5_NewThread2();
		
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
