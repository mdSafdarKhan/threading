package executor_service;

import java.util.concurrent.Callable;

public class Factorial implements Callable<Integer> {

	int n;

	Factorial(int n) {
		this.n = n;
	}

	@Override
	public Integer call() {
		int sum = 1;
		for(int i=n; i>0; i--) {
			sum = sum * i;
		}
		return sum;
	}

}
