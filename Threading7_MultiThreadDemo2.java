package threading;

public class Threading7_MultiThreadDemo2 {
	public static void main(String[] args) {
		Threading4_NewThread n1 = new Threading4_NewThread("One");
		Threading4_NewThread n2 = new Threading4_NewThread("Two");
		Threading4_NewThread n3 = new Threading4_NewThread("Three");
		
		System.out.println("n1 is alive: " + n1.t.isAlive());
		System.out.println("n2 is alive: " + n2.t.isAlive());
		System.out.println("n3 is alive: " + n3.t.isAlive());
		
		try {
			n1.t.join();
			n2.t.join();
			n3.t.join();
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted...");
		}
		
		System.out.println("n1 is alive: " + n1.t.isAlive());
		System.out.println("n2 is alive: " + n2.t.isAlive());
		System.out.println("n3 is alive: " + n3.t.isAlive());
		
		System.out.println("Main thread exited...");
	}
}
