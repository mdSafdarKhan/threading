package threading;

class Callme {

	//public void call(String msg) {
	public synchronized void call(String msg) {
		System.out.print("[" + msg);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("]");
	}
}

class Caller implements Runnable{

	Callme callme;
	String msg;
	Thread t;
	
	public Caller(Callme callme, String msg) {
		this.callme = callme;
		this.msg = msg;
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		callme.call(msg);
	}
}

public class Threading8_Sync{
	public static void main(String[] args) {
		Callme callme = new Callme();
		
		Caller c1 = new Caller(callme, "Hello");
		Caller c2 = new Caller(callme, "Synchronized");
		Caller c3 = new Caller(callme, "World");
		
		//wait for threads to end
		try {
			c1.t.join();
			c2.t.join();
			c3.t.join();
		} catch (InterruptedException e) {
			System.out.println("Main interrupted.");
		}
	}
}
