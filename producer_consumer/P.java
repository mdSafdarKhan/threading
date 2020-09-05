package producer_consumer;

public class P implements Runnable{

	Q q;
	
	P(Q q){
		this.q =q;
	}
	
	@Override
	public void run() {
		try {
			int i =0;
			while(true)
				q.produce(i++);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
