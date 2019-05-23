package pers.allen.explore.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：执行一个或多个线程时，等待另一个（组）线程完成时，再继续执行
 * @author lengyul
 *
 */
public class CountDownLatchTest {
		
	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch latch =new CountDownLatch(5);
		LatchApp ld = new LatchApp(latch);
		
		Instant start = Instant.now();
		for (int i = 0; i < 5; i++) {
			new Thread(ld).start();
		}
		
		latch.await();
		
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);
		System.out.println("任务耗费时间："+duration.getSeconds()+"s"); 
	}
	
	public static class LatchApp implements Runnable{
		
		private CountDownLatch latch;
		
		public LatchApp(CountDownLatch latch){
			this.latch = latch;
		}
		
		@Override
		public void run() {
			synchronized (this) {
			try {			
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{			
				latch.countDown();// - 1
				}
			}
		}
		
	}
}


