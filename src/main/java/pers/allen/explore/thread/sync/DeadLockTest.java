package pers.allen.explore.thread.sync;


/**
 * 死锁：当一组进程或线程中的每一个都在等待一个只有该组中的另一个进程才能引用的事件时，称这组进程或线程死锁了。
 * @author lengyul
 * @date 2018年12月21日 上午10:19:21
 * 死锁最简单的情形是：线程A持有对象X的独占锁，并在等待对象Y的锁，而线程B持有对象Y的独占锁，却在等待对象X的锁。
 */
public class DeadLockTest {

	public static void main(String[] args) {
		
		DeadLock dl = new DeadLock();
		Thread t1 = new Thread(dl,"A");
		Thread t2 = new Thread(dl,"B");
		t1.start();
		t2.start();
	}
	
	private static class DeadLock implements Runnable {
		Object X = new Object();
		Object Y = new Object();
		@Override
		public void run() {
			if (Thread.currentThread().getName().equals("A")) {
				synchronized (X) { // 线程A 持有X对象
					System.out.println("X " + Thread.currentThread().getName());
					synchronized (Y) { // 线程A获取未释放的Y对象
						System.out.println(Thread.currentThread().getName());
					}
				}
			} else {
				synchronized (Y) { // 线程B 持有Y对象
					System.out.println("Y " + Thread.currentThread().getName());
					synchronized (X) { // 线程B获取未释放的X对象
						System.out.println(Thread.currentThread().getName());
					}
				}
			}
			System.out.println("---------------------------end");
		}
	}
	
	
	
	
	
	
	
}

