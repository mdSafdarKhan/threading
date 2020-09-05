package deadlock;

public class Main{

	public static void main(String[] args) {
		Class1 class1 = new Class1();
		Class2 class2 = new Class2();
		
		new Thread1(class1, class2);
		new Thread2(class1, class2);
	}

}
