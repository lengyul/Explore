package com.expolre.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用于解决多线程问题的方式： synchronized 1.同步代码块 2.同步方法
 * 
 * 3.同步锁 Lock： 通过lock()方法上锁，必须通过unlock()方法进行释放锁。 更加灵活
 * 
 * @author lengyul
 *
 */
public class LockTest {

	public static void main(String[] args) {
		Ticket ticket = new Ticket();

		new Thread(ticket).start();
		new Thread(ticket).start();
		new Thread(ticket).start();
	}

}

class Ticket implements Runnable {
	private int tick = 100;
	
	private Lock lock = new ReentrantLock();
	/*
	 * 使用jdk Lock锁解决(non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			lock.lock();
			if (tick > 0) {
				try {
					Thread.sleep(200);
					System.out.println(Thread.currentThread().getName() + "完成售票，余票为" + --tick);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				 	}	
				}
			}
		}
	/*
	 * 使用 synchronized 线程通讯解决
	 */
	/*@Override
	public void run() {
		while (true) {
			if (tick > 0) {
				synchronized (this) {
					notify();
					System.out.println(Thread.currentThread().getName() + "完成售票，余票为" + --tick);
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}*/

}
