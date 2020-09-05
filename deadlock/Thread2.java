package deadlock;

public class Thread2 implements Runnable{

	Class1 class1;
	Class2 class2;
	
	Thread2(Class1 class1, Class2 class2){
		this.class1=class1;
		this.class2=class2;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		class2.m2(class1);
	}

}
