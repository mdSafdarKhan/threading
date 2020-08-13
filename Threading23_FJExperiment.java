package threading;

//A simple program that lets you experiment with the effects of
//changing the threshold and parallelism of a ForkJoinTask.

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

//A ForkJoinTask (via RecursiveAction) that performs a
//a transform on the elements of an array of doubles.
class Transform extends RecursiveAction {

	private static final long serialVersionUID = 1L;

	// Array to be accessed.
	double[] nums;

	// Determines what part of data to process.
	int start, end;

	// Sequential threshold, which is set by the constructor.
	int threshold;

	Transform(double[] n, int s, int e, int t) {
		nums = n;
		start = s;
		end = e;
		threshold = t;
	}

	// This is the method in which parallel computation will occur.
	@Override
	protected void compute() {

		// If number of elements is below the sequential threshold,
		// then process sequentially.
		if ((end - start) < threshold) {
			// The following code assigns an element at an even index the
			// square root of its original value. An element at an odd
			// index is assigned its cube root. This code is designed
			// to simply consume CPU time so that the effects of concurrent
			// execution are more readily observable.
			for (int i = start; i < end; i++) {
				if ((nums[i] % 2) == 0) {
					nums[i] = Math.sqrt(nums[i]);
				} else {
					nums[i] = Math.cbrt(nums[i]);
				}
			}
		} else {
			// Otherwise, continue to break the data into smaller pieces.

			// Find the midpoint.
			int mid = (start + end) / 2;

			invokeAll(new Transform(nums, start, mid, threshold), 
					new Transform(nums, mid, end, threshold));
		}
	}

}

//Demonstrate parallel execution
public class Threading23_FJExperiment {

	public static void main(String[] args) {
		//int pLevel = 1;
		//int threshold = 1000;
		
//		int pLevel = 2;
//		int threshold = 1000;
		
//		int pLevel = 4;
//		int threshold = 1000;
		
//		int pLevel = 4;
//		int threshold = 5000;
		
		int pLevel = 4;
		int threshold = 10000;

		// These variables are used to time the task.
		long beginT, endT;

		// Create a task pool. Notice that the parallelism level is set.
		ForkJoinPool fjp = new ForkJoinPool(pLevel);

		double[] nums = new double[1000000];

		//Put data into empty array.
		for (int i = 0; i < nums.length; i++) {
			nums[i] = (double) i;
		}
		
		Transform transform = new Transform(nums, 0, nums.length, threshold);
		
		//Start timing.
		beginT = System.nanoTime();
		
		//Start the main ForkJoinPool
		fjp.invoke(transform);
		
		//End timing.
		endT = System.nanoTime();
		
		System.out.println("Level of parallelism: " + pLevel);
		System.out.println("Sequential threshold: " + threshold);
		System.out.println("Elapsed time: " + (endT - beginT) + " ns");
		System.out.println();

	}

}
