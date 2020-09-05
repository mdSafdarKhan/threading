package semaphore;

import java.util.concurrent.Semaphore;

public class Main implements Runnable {

	Semaphore sem;
	Table table;
	int n;

	Main(Semaphore sem, Table table, int n) {
		this.sem = sem;
		this.table = table;
		this.n = n;
		new Thread(this).start();
	}

	@Override
	public void run() {
		table.count(sem, n);
	}

	public static void main(String[] args) {
		Semaphore sem = new Semaphore(1);
		Table table = new Table();
		new Main(sem, table, 2);
		new Main(sem, table, 17);
	}

}
