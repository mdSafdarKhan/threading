package threading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Task1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		return "task1 done";
	}
	
}
class Task2 implements Callable<String>{

	@Override
	public String call() throws Exception {
		return "task2 done";
	}
	
}
class Task3 implements Callable<String>{

	@Override
	public String call() throws Exception {
		//Thread.sleep(20);
		return "task3 done";
	}
	
}
public class Threading19_CallableDemo {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(3);
		
		System.out.println("starting");
		
		Future<String> f1 = es.submit(new Task1());
		Future<String> f2 = es.submit(new Task2());
		Future<String> f3 = es.submit(new Task3());
		
		try {
			/*
			System.out.println(f1.get());
			System.out.println(f2.get());
			System.out.println(f3.get());
			*/
			System.out.println(f1.get(10, TimeUnit.MILLISECONDS));
			System.out.println(f2.get(10, TimeUnit.MILLISECONDS));
			System.out.println(f3.get(10, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		es.shutdown();
		System.out.println("done");
	}
}
