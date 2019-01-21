package com.explore.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;


public class ForkJoinPoolTest {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalc(0L, 1000000000L);
		
		long sum = pool.invoke(task);
		System.out.println(sum);
		
		/*Long sum  = LongStream.rangeClosed(0L,1000000000L)
				.parallel()
				.reduce(0L,Long::sum);
		System.out.println(sum);*/
	}
}

class ForkJoinCalc extends RecursiveTask<Long>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5171898378238934593L;
	private long start;
	private long end;
	
	private static final long THRESHOLD = 10000L; //临界值
	
	public ForkJoinCalc(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute() {
		long length = end - start;
		if(length <= THRESHOLD){
			long sum = 0;
			
			for (long i = start; i <= end; i++) {
				sum += i;
			}
			
			return sum;
		}else{
			long middle = (start + end) / 2;
			
			ForkJoinCalc left = new ForkJoinCalc(start, middle);
			left.fork(); //拆分，并将该子任务压入线程队列
			
			ForkJoinCalc right = new ForkJoinCalc(middle+1, end);
			right.fork();
			
			return left.join() + right.join();
		}
	}
	
}