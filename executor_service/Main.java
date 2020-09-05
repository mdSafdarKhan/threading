package executor_service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Factorial t0 = new Factorial(0);
		Factorial t1 = new Factorial(1);
		Factorial t2 = new Factorial(2);
		Factorial t3 = new Factorial(3);
		Factorial t4 = new Factorial(4);
		Factorial t5 = new Factorial(5);
		
		ExecutorService e = Executors.newFixedThreadPool(1);
		Future<Integer> f0 = e.submit(t0);
		Future<Integer> f1 = e.submit(t1);
		Future<Integer> f2 = e.submit(t2);
		Future<Integer> f3 = e.submit(t3);
		Future<Integer> f4 = e.submit(t4);
		Future<Integer> f5 = e.submit(t5);
		
		System.out.println("0: " + f0.get());
		System.out.println("1: " + f1.get());
		System.out.println("2: " + f2.get());
		System.out.println("3: " + f3.get());
		System.out.println("4: " + f4.get());
		System.out.println("5: " + f5.get());
		
		e.shutdown();
	}

}
