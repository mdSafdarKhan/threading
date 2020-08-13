package threading;

//A simple example that uses RecursiveTask<V>.

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

//A RecursiveTask that computes the summation of an array of doubles.
class Sum extends RecursiveTask<Double>{

	//The sequential threshold value.
	final int seqThreshold = 500;
	
	//Array to be accessed.
	double[] nums;
	
	//Determines what part of data to be processed.
	int start, end;
	
	Sum(double[] data, int s, int e){
		nums = data;
		start = s;
		end = e;
	}
	
	//Find the summation of an array of doubles.
	@Override
	protected Double compute() {
		double sum = 0;
		
		// If number of elements is below the sequential threshold,
		// then process sequentially.
		if((end-start) < seqThreshold) {
			for(int i=start; i<end; i++) {
				sum +=nums[i];
			}
		}
		else {
			System.out.println("*");
			//Otherwise, continue to break the data into smaller pieces.
			
			//Find the middle
			int mid = (start + end)/2;
			
			//Invoke new tasks, using the subdivided data.
			Sum subTaskA = new Sum(nums, start, mid);
			Sum subTaskB = new Sum(nums, mid, end);
			
			//Start each subtask by forking.
			subTaskA.fork();
			subTaskB.fork();
			
			//Wait for the subtask to return, and aggregate the results.
			sum = subTaskA.join() + subTaskB.join();
			
		}
		
		//Return the final sum.
		return sum;
	}
	
}

//Demonstrate parallel execution.
public class Threading24_RecurTaskDemo {

	public static void main(String[] args) {
		//Create a task pool.
		ForkJoinPool fjp = new ForkJoinPool();
		
		double[] nums = new double[5000];
		
		// Initialize nums with values that alternate between
		// positive and negative.
		for(int i=0; i<nums.length; i++) {
			nums[i] = (double)((i%2 == 0) ? i : -i);
		}
		
		Sum task = new Sum(nums, 0, nums.length);
		
		//Start the ForkJoinTasks. Notice tha in this case, invoke() return a result.
		
		double sum = fjp.invoke(task);
		System.out.println("sum: " + sum);
	}

}
