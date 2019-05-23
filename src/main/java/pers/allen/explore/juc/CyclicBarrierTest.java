package pers.allen.explore.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 循环障碍：允许一组线程之间相互等待，达到一个共同点，再继续执行
 * @author lengyul
 * @date 2019年5月23日 下午3:31:17
 */
public class CyclicBarrierTest {

	public static void main(String[] args) throws InterruptedException {
		
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3); // parties
		BarrierApp barrierApp = new BarrierApp(cyclicBarrier);
		
		for (int i = 0; i < 3; i++) {
			new Thread(barrierApp).start();
		}
		
		TimeUnit.SECONDS.sleep(2);
		cyclicBarrier.reset(); // 计数器可以被重置  count = parties;
		
		System.out.println("-----------------------------");
		for (int i = 0; i < 3; i++) {
			new Thread(barrierApp).start();
		}
	}
	
	private static class BarrierApp implements Runnable {

		private CyclicBarrier cyclicBarrier;

		public BarrierApp(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " run...");
			try {
				cyclicBarrier.await(); // 等待其他线程 count--
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " end...");
		}

	}
	
}
