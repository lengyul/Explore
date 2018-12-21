package com.expolre.thread.pool;

public class ThreadsTest {
	public static void main(String[] args) throws InterruptedException {
		
		WorkQueue workQueue = new WorkQueue(5);
		
		Thread.sleep(1000);
		System.out.println("当前线程的线程组中的所有子组数量为："+Thread.activeCount());
		
		workQueue.execute(() ->{
			System.out.println("线程"+Thread.currentThread().getName()+"正在执行");
		});
		
		workQueue.execute(() ->{
			System.out.println("线程"+Thread.currentThread().getName()+"正在执行");
		});
		
		workQueue.execute(() ->{
			System.out.println("线程"+Thread.currentThread().getName()+"正在执行");
		});
		
	}
}
