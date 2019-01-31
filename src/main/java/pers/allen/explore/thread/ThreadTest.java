package pers.allen.explore.thread;

import java.util.Objects;

import org.junit.Test;

import pers.allen.explore.thread.lock.ThreadLock;

public class ThreadTest {

	/**
	 * print 100
	 */
	@Test
	public void testThreadLocal() {

		ThreadLocalNumber tn = new ThreadLocalNumber();

		new Thread(() -> {
			// for (int i = 1; i <= 100; i++) {
			tn.printA();
			// }
		}).start();

		new Thread(() -> {
			// for (int i = 1; i <= 100; i++) {
			tn.printB();
			// }
		}).start();

		new Thread(() -> {
			// for (int i = 1; i <= 100; i++) {
			tn.printC();
			// }
		}).start();
	}

	/**
	 * Lock测试
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testLock() throws InterruptedException {
		ThreadLock t = new ThreadLock();

		/*
		 * Thread t1 = new Thread( () -> t.run(Thread.currentThread())); Thread
		 * t2 = new Thread( () -> t.run(Thread.currentThread())); t1.start();
		 * t2.start();
		 */

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					t.runWaitTime(Thread.currentThread());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					t.runWaitTime(Thread.currentThread());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

	}

}
