package threading;

class A {

	public synchronized void foo(B b) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " entered A.foo");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("A Interrupted");
		}
		System.out.println("trying to call B.last");
		
		b.last();
	}

	public synchronized void last() {
		System.out.println("Inside A.last");
	}
}

class B {
	public synchronized void bar(A a) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " entered B.bar");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("B Interrupted");
		}
		System.out.println("trying to call A.last");
		
		a.last();
	}

	public synchronized void last() {
		System.out.println("Inside B.last");
	}
}

public class Threading12_Deadlock extends Thread{
	
	A a = new A();
	B b = new B();
	
	public void m1() {
		this.start();
		a.foo(b); //run by main thread
	}
	
	@Override
	public void run() {
		b.bar(a);  //run by d1 thread
	}
	
	public static void main(String[] args) {
		Threading12_Deadlock d1 = new Threading12_Deadlock();
		d1.m1();
	}

	
}
