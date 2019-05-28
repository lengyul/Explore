package pers.allen.explore.juc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池：提供一个线程队列，队列中保存着所有等待的线程。避免频繁创建线程和销毁线程，提高响应速度。
 * @author lengyul
 * 线程池的体系结构：
 * 	java.util.concurrent.Executor：负责线程的使用与调度的根接口
 *   	---ExecutorService 子接口：线程池的主要接口
 *   		---ThreadPoolExecutor：线程池的实现类
 *   		---ScheduledExecutorService 子接口：负责线程的调度
 *   		   ---ScheduledThreadPoolExecutor：继承ThreadPoolExecutor，实现ScheduledExecutorService
 *   
 * 工具类：Executors
 * ExecutorService newFixedThreadPool(int nThreads) ：创建一个指定大小的线程池
 * ExecutorService newCachedThreadPool()： 缓存线程池，数量不固定，根据需求自动的更改数量
 * ExecutorService newSingleThreadExecutor()：创建单个线程池，线程池中只有一个线程 
 * ScheduledExecutorService newScheduledThreadPool(int corePoolSize)：创建固定大小的线程池，可以延迟或执行定时任务
 * 
 */
public class ThreadPoolTest {
	
	public static void main(String[] args) {
		ThreadPoolApp tpd = new ThreadPoolApp();
		// 创建固定大小线程池
		/*ExecutorService ex = Executors.newFixedThreadPool(5);
		// 创建自定义线程池
		new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
		
		for (int i = 0; i < 10; i++) {			
			ex.submit(tpd);
		}
		ex.shutdown(); // 等待所有任务完成后关闭
*/		//ex.shutdownNow();// 直接关闭
		// 线程调度 定时执行
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
		ses.schedule(tpd,3000,TimeUnit.MILLISECONDS);
		ses.shutdown();
	}
	
	private static class ThreadPoolApp implements Runnable {
		
		private int number  =  0 ;
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"："+number++);
		}
		
	}
	
}

