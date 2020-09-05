package exchanger;

import java.util.concurrent.Exchanger;

public class Main {

	public static void main(String[] args) {
		Exchanger<String> ex = new Exchanger<>();
		new Sender(ex);
		new Receiver(ex);
	}

}
