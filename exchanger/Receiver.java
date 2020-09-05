package exchanger;

import java.util.concurrent.Exchanger;

public class Receiver implements Runnable{

	Exchanger<String> ex;
	
	Receiver(Exchanger<String> ex){
		this.ex=ex;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			String address = ex.exchange(new String());
			System.out.println("address " + address);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
