package pers.allen.explore.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import pers.allen.explore.thread.ThreadImpl;

/**
 * Future：用于获取异步任务的执行结果
 * @author lengyul
 * @see Callable FutureTask
 * 
 * Callable接口：创建线程的第三种方式，允许带返回值，并且可以抛出异常
 * @see ThreadImpl
 * 执行Callable，需要FutureTask类或者线程池的支持用于返回Future接收结果
 * Callable用于产生结果，Future用于获取结果
 */
public class FutureTest {
		
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableApp ca = new CallableApp();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		// 启动 Callable 线程
		Future<String> task = null;
		task = executorService.submit(ca); // 使用线程池执行
		 		
		
		// 使用FutureTask执行，实现了Runnable和Future接口
		/*FutureTask<String> futureTask = new FutureTask<>(ca);
		new Thread(futureTask).start(); */
		
		// 获取结果线程
		CompletedResult cr = new CompletedResult(task); 
		new Thread(cr).start();
		
		executorService.shutdown();
	} 
	
	private static class CallableApp implements Callable<String> {

		@Override
		public String call() throws Exception {
			Thread.sleep(2000);
			return "success";
		}
	}

	private static class CompletedResult implements Runnable {

		public Future<String> futureTask;
		
		public CompletedResult(Future<String> futureTask){
			this.futureTask = futureTask;
		}
		
		@Override
		public void run() {
			try {
				System.out.println("-----------------------start");
				String result = futureTask.get();
				if ("success".equals(result)) {
					System.out.println(this.getClass());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}
}

