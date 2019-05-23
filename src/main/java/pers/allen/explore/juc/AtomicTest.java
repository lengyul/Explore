package pers.allen.explore.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性：提供了互斥访问，意味着同一时刻保证只有一个线程对共享数据进行读写操作
 * @author lengyul
 * @see CompareAndSwapMockTest
 * 原子变量：
 * 1.保证内存可见性
 * 2.CAS(Compare-And-Swap)算法保证数据的原子性，具体实现于Unsafe类
 * (CAS操作需要输入两个数值，一个旧值（期望操作前的值）和一个新值，
 * 在操作期间先比较下旧值有没有发生变化，如果没有发生变化，才交换成新值，发生了变化则不交换)
 */
public class AtomicTest {
	
	public static void main(String[] args) throws InterruptedException {	
	
		AtomicInteger count = new AtomicInteger(0);
		CountDownLatch countDownLatch =  new CountDownLatch(20);
		
		for (int i = 0; i < 20; i++) {
			new Thread(() -> {
				count.incrementAndGet();
				countDownLatch.countDown();
			}).start();
		}
		
		countDownLatch.await();
		System.out.println("count：" + count.get());
		
	}
}
