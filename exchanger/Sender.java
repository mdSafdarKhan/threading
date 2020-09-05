package exchanger;

import java.util.concurrent.Exchanger;

public class Sender implements Runnable{

	Exchanger<String> ex;
	
	Sender(Exchanger<String> ex){
		this.ex=ex;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			ex.exchange("Kembanghan, Sinapore");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
