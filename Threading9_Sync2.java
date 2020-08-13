package threading;

//This program uses a synchronized block.
class Callme2 {

	public void call(String msg) {
		System.out.print("[" + msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("]");
	}
}

class Caller2 implements Runnable{

	Callme2 callme;
	String msg;
	Thread t;
	
	public Caller2(Callme2 callme, String msg) {
		this.callme = callme;
		this.msg = msg;
		t = new Thread(this);
		t.start();
	}
	
	// synchronize calls to call()
	@Override
	public void run() {
		synchronized (callme) {	// synchronized block
			callme.call(msg);
		}
	}
}

public class Threading9_Sync2{
	public static void main(String[] args) {
		Callme2 callme = new Callme2();
		
		Caller2 c1 = new Caller2(callme, "Hello2");
		Caller2 c2 = new Caller2(callme, "Synchronized2");
		Caller2 c3 = new Caller2(callme, "World2");
		
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
