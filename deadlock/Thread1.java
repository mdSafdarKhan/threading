package deadlock;

public class Thread1 implements Runnable{

	Class1 class1;
	Class2 class2;
	
	Thread1(Class1 class1, Class2 class2){
		this.class1=class1;
		this.class2=class2;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		class1.m1(class2);
	}

}
