package producer_consumer;

public class Main {

	public static void main(String[] args) {
		Q q = new Q();
		P p = new P(q);
		C c = new C(q);
		
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(c);
		
		t1.start();
		t2.start();
		
		System.out.println("main ends");
	}

}
