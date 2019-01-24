package com.explore.thread.message;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.explore.utils.RandomUtils;


public class ThreadMessageTest {
		
	@Test
	public void testObject() throws InterruptedException {
		LockObject obj = new LockObject();
		
		new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			obj.doNotify();
		}).start();
		
		
		obj.doWait();
		
		
		
		/*System.out.println(LocalDateTime.now());
		Thread t1 = new Thread(new Thread1("任务0", obj));
		Thread t2 = new Thread(new Thread2("任务1", obj));

		t1.start();
		t2.start();

		obj.doWait(1000 * 60 * 1); // 等待一分钟
		System.out.println(LocalDateTime.now() + ":" + obj.getMessage());*/
	}

	@Test
	public void testObjects() throws InterruptedException {
		
		Object object3 = new Object();
		
		for (int i = 0; i < 10; i++) {
		//	Thread.sleep(200);
			new Thread(() -> {			
				LockObjects.wait("object1"+RandomUtils.randomNumeric(3));
			}).start();
		}
		
	
		/*new Thread(() -> {			
			LockObjects.wait("object1");
			System.out.println("----object2");
		}).start();*/
		
		/*new Thread(() -> {			
			LockObjects.wait("object2", object3);
			System.out.println("object2");
		}).start();
		
		new Thread(() -> {			
			LockObjects.wait("object3", object3);
			System.out.println("object3");
		}).start();*/
		
		
		TimeUnit.SECONDS.sleep(3);
		
		System.out.println(LockObjects.getWaitingSize());
		LockObjects.notifyValues();
		//LockObjects.notify("object2");
		System.out.println(LockObjects.getWaitingSize());
		synchronized (this) {
			this.wait();
		}
	}
	
	
	/*
	 *  object1对象调用wait()方法后，当前线程会进入阻塞状态，
	 * 并且释放object1对象的锁，object1对象调用notifyAll()方法后，
	 * 会唤醒所有正在等待（对象）的线程[同一时刻只能有一个线程可以获取到锁，其他线程必须等待持有锁的线程释放锁]。
	 * 	总结：同一个对象可以在不同线程中多次调用wait()方法，当第一个线程执行wait()方法后，会释放当前锁，
	 * 第n个线程获取到对象的锁后，进行wait()方法后，再次释放。
	 *  	 notify()方法和notifyAll()方法调用时如果没有正在等待的对象也不会发生异常，notify()方法会
	 *  随机唤醒一个正在等待（对象）的线程。
	 */
	@Test
	public void testWaitNotifyAll() throws InterruptedException {
		Object object1 = new Object();
		Object object2 = new Object();
		
		new Thread(() -> {
			synchronized (object1) {
				try {
					object1.wait();
					System.out.println("----------------1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(() -> {
			synchronized (object1) {
				try {
					object1.wait();
					System.out.println("----------------0");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (object1) {
			object1.notifyAll();
		}
		synchronized (object2) {
			object2.wait();
		}
		System.out.println(" ------------------------ ");
	}
	
}
