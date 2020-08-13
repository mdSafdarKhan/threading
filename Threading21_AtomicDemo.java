package threading;

import java.util.concurrent.atomic.AtomicInteger;

class Shared2{
	public static AtomicInteger ai = new AtomicInteger(0);
}

//A thread of execution that increments count.
class AtomThread implements Runnable {
	String name;
	public AtomThread(String name) {
		this.name = name;
		new Thread(this, name).start();
	}
	@Override
	public void run() {
		System.out.println("starting " + name);
		for(int i=1; i<=3; i++) {
			System.out.println(name + " got " + Shared2.ai.getAndSet(i));
		}
	}
}
public class Threading21_AtomicDemo {

	public static void main(String[] args) {
		new AtomThread("A");
		new AtomThread("B");
		new AtomThread("C");
	}

}
