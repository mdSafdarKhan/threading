package producer_consumer;

public class C implements Runnable{

	Q q;
	
	C(Q q){
		this.q= q;
	}
	
	@Override
	public void run() {
		try {
			while(true)
				q.consume();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
