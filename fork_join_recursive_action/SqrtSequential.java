package fork_join_recursive_action;

public class SqrtSequential {

	public void sqrt(double n) {
		long begin = System.currentTimeMillis();
		for(double i=1; i<=n; i++) {
			System.out.println(i + ": " + Math.sqrt(i));
		}
		long end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end-begin));
	}
}
