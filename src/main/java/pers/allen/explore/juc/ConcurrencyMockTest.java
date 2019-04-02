package pers.allen.explore.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 并发模拟测试（Semaphore限制线程数量，CountDownLatch等待请求线程执行完成）
 * @author lengyul
 * @date 2019年4月1日 下午8:08:40
 */
public class ConcurrencyMockTest {
	
	private static int threadTotal = 100;
	private static int requestTotal = 5000;
	private static int count = 0;
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaphore  = new Semaphore(threadTotal);
		CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
		for (int i = 0; i < requestTotal; i++) {
			executorService.execute(new Thread(() ->{
				try {
					semaphore.acquire();
					increment();
					semaphore.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			}));
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println("count：" + count);
	}
	
	private static void increment() {
		count++;
	}
	
}
