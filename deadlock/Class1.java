package deadlock;

public class Class1 {

	public synchronized void m1(Class2 class2) {
		System.out.println("m1 running...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("calling n2...");
		class2.n2();
		System.out.println("n2 called...");
	}
	
	public synchronized void n1() {
		
	}
}
