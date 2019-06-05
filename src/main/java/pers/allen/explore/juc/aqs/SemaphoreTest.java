package pers.allen.explore.juc.aqs;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 计数信号量：用来控制同时访问某个特定资源请求（线程/操作）的数量
 * @author lengyul
 * @date 2019年1月2日 下午4:09:31
 */
public class SemaphoreTest {
	
	public static void main(String[] args) {
		SemApp sem = new SemApp();
		//10 个线程都在运行，只有 3 个线程是活跃的，在一个信号计数器释放之前，其他 7 个线程都处于空闲状态。
		for (int i = 0; i < 10; i++) {
			new Thread(sem).start();
		}
	}
	
	private static class SemApp implements Runnable{
		final Random rand = new Random();
		final Semaphore available = new Semaphore(3);
		int count = 0;
		@Override	
		public void run() {
			int time = rand.nextInt(15);
			int num  = count++;
			
			try {
				available.acquire();
				System.out.println("Executing long-running action for " + time + " seconds... #" + num);
				
				Thread.sleep(time * 1000);
				
				System.out.println(Thread.currentThread().getName()+"Done with #" + num + "!");
				available.release(); 
			//	available.release(permits); 释放多个permit
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}