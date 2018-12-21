package com.expolre.thread.sync;

/**
 * 死锁：当一组进程或线程中的每一个都在等待一个只有该组中的另一个进程才能引用的事件时，称这组进程或线程死锁了。
 * @author lengyul
 * @date 2018年12月21日 上午10:19:21
 * 死锁最简单的情形是：线程A持有对象X的独占锁，并在等待对象Y的锁，而线程B持有对象Y的独占锁，却在等待对象X的锁。
 */
public class DeadLockTest {

	public static void main(String[] args) {
		
		DeadLock dl = new DeadLock();
		Thread t1 = new Thread(dl,"t1");
		Thread t2 = new Thread(dl,"t2");
		
		t1.start();
		t2.start();
		
	}
	
}
class DeadLock implements Runnable{

	Object obj1 = new Object();
	Object obj2 = new Object();
	
	@Override
	public void run() {
		
		if (Thread.currentThread().getName().equals("t1")) {
			synchronized (obj1) {
				System.out.println("obj1 "+Thread.currentThread().getName());
				synchronized (obj2) {
					System.out.println(Thread.currentThread().getName());
				}
			}
		}else{
			synchronized (obj2) {
				System.out.println("obj2 "+Thread.currentThread().getName());
				synchronized (obj1) {
					System.out.println(Thread.currentThread().getName());
				}
			}
		}
		System.out.println("---------------------------end");
	}
	
}
