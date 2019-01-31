package pers.allen.explore.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性：是指一个操作或多个操作要么全部执行，且执行的过程不会被任何因素打断，要么就都不执行。
 * @author lengyul
 * 原子变量：
 * 1.保证内存可见性
 * 2.CAS(Compare-And-Swap)算法保证数据的原子性
 * (CAS操作需要输入两个数值，一个旧值（期望操作前的值）和一个新值，
 * 在操作期间先比较下旧值有没有发生变化，如果没有发生变化，才交换成新值，发生了变化则不交换。)
 */
public class AtomicTest {
	
	public static void main(String[] args) {	
	
		AtomicDemo ad =new AtomicDemo();
		
		for (int i = 0; i < 10; i++) {
			new Thread(ad).start();
		}
	}
}

class AtomicDemo implements Runnable {

	//private volatile int number = 0;
	private AtomicInteger number  = new AtomicInteger();
	
	@Override
	public void run() {
		try {
			//Thread.sleep(500);
			System.out.println(Thread.currentThread().getName() + "：" + getNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNumber() {
		return number.getAndIncrement();
	}

	

}