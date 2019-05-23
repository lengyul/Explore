package pers.allen.explore.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import pers.allen.explore.thread.sync.SynchronizedTest;

/**
 * 用于解决多线程并发问题的方式： 
 * @author lengyul
 * @see SynchronizedTest
 * @see Lock#ReentrantLock
 * 
 * Lock： 通过lock()加锁，必须使用unlock()手动释放锁
 * 与synchronized相比更加灵活、可中断（等待）锁、公平锁、多条件变量 Condition
 * 
 * 笔记：{@link #http://note.youdao.com/noteshare?id=eb20eaef9dd06bea231a64d956ca838e&sub=795DF88616F34E23B409793174E94BDC}
 */
public class ReentrantLockTest {

	public static void main(String[] args) {
		TicketApp ticket = new TicketApp();

		new Thread(ticket).start();
		new Thread(ticket).start();
		new Thread(ticket).start();
	}

	private static class TicketApp implements Runnable {
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
}


