package threading;

import java.util.concurrent.ForkJoinPool;

//A simple example of the basic divide-and-conquer strategy.
//In this case, RecursiveAction is used.

import java.util.concurrent.RecursiveAction;

//A ForkJoinTask (via RecursiveAction) that transforms
//the elements in an array of doubles into their square roots.
class SqrtTransform extends RecursiveAction {

	private static final long serialVersionUID = 1L;

	// The threshold value is arbitrarily set at 1,000 in this example.
	// In real-world code, its optimal value can be determined by
	// profiling and experimentation.
	final int sqrtThreshold = 1000;

	// Array to be accessed
	double[] nums;

	// Determines what part of data to process
	int start, end;

	public SqrtTransform(double[] nums, int start, int end) {
		this.nums = nums;
		this.start = start;
		this.end = end;
	}

	// This is the method in which parallel computation will occur.
	@Override
	protected void compute() {

		// If the number of elements is below the sequential threshold, then process
		// sequentially.
		if ((end - start) < sqrtThreshold) {

			// Transform each element in its square root.
			for (int i = start; i < end; i++) {
				nums[i] = Math.sqrt(nums[i]);
			}
		} else {

			// Otherwise continue to break the data into smaller pieces

			// Find the mid point
			int mid = (start + end) / 2;

			//Invoke the new tasks using the subdivided data.
			invokeAll(new SqrtTransform(nums, start, mid), new SqrtTransform(nums, mid, end));

		}
	}

}

//Demonstrate parallel execution.
public class Threading22_ForkJoinDemo {
	public static void main(String[] args) {
		// Create a task pool
		ForkJoinPool fjp = new ForkJoinPool();
		//ForkJoinPool fjp = ForkJoinPool.commonPool();//Another way to get ForkJoinPool

		double[] nums = new double[100000];

		// Give nums some value
		for (int i = 0; i < nums.length; i++) {
			nums[i] = (double) i;
		}

		System.out.println("A portion of the original sequence.");

		for (int i = 0; i < 10; i++) {
			System.out.print(nums[i]);
		}
		System.out.println("\n");

		SqrtTransform sqrtTransform = new SqrtTransform(nums, 0, nums.length);
		
		//Start the main ForkJoinTask
		fjp.invoke(sqrtTransform);
		
		System.out.println("A portion of the transformed sequence upto four decimal places.");
		
		for(int i=0; i<10; i++) {
			System.out.format("%.4f ", nums[i]);
		}
		System.out.println("\n");
	}
}
