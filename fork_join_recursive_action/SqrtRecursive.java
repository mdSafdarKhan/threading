package fork_join_recursive_action;

import java.util.concurrent.RecursiveAction;

public class SqrtRecursive extends RecursiveAction{

	int threshold = 1000;
	double n;
	int start, end;
	
	public SqrtRecursive(double n, int start, int end) {
		this.n=n;
		this.start=start;
		this.end=end;
	}
	
	@Override
	protected void compute() {
		
	}

}
