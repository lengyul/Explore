package pers.allen.explore.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable接口：
 * 1.允许带返回值，并且可以抛出异常
 * 2.执行Callable，需要FutureTask类的支持用于接收结果
 * @author lengyul
 * Callable用于产生结果，Future用于获取结果
 */
public class CallableTest {
		
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableApp ca = new CallableApp();
		FutureTask<String> ft = new FutureTask<>(ca);
		CompletedResult cr = new CompletedResult(ft);
		
		new Thread(ft).start();
		new Thread(cr).start();;	
	} 
	
	private static class CallableApp implements Callable<String> {

		@Override
		public String call() throws Exception {
			Thread.sleep(2000);
			return "success";
		}
	}

	private static class CompletedResult implements Runnable {

		public FutureTask<String> futureTask;
		
		public CompletedResult(FutureTask<String> futureTask){
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

