package deadlock;

public class Class2 {
	
	public synchronized void m2(Class1 class1) {
		System.out.println("m2 running...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("calling n1...");
		class1.n1();
		System.out.println("n1 called...");
	}
	
	public synchronized void n2() {
		
	}
}
