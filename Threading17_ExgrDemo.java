package threading;

import java.util.concurrent.Exchanger;

//A Thread that uses a string.
class UseString implements Runnable{
	
	Exchanger<String> exgr;
	
	public UseString(Exchanger<String> exgr) {
		this.exgr = exgr;
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			// Exchange an empty buffer for a full one.
			String data = exgr.exchange(new String());
			System.out.println("got: " + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//A Thread that constructs a string.
class MakeString implements Runnable{
	
	Exchanger<String> exgr;
	
	public MakeString(Exchanger<String> exgr) {
		this.exgr = exgr;
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			// Exchange a full buffer for an empty one.
			exgr.exchange("Apple");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class Threading17_ExgrDemo {
	public static void main(String[] args) {
		Exchanger<String> exgr = new Exchanger<>();
		
		new UseString(exgr);
		new MakeString(exgr);
	}
}
