package com.expolre.thread;

import java.util.Objects;

import org.junit.Test;

import com.expolre.thread.lock.ThreadLock;


public class ThreadTest {
	
	
	public static void main(String[] args) {
		
		ThreadNumber tn = new ThreadNumber();
		
		new Thread(() ->{	
			//for (int i = 1; i <= 100; i++) {				
				tn.printA();
			//}
		}).start();
		
		new Thread(() ->{
			//for (int i = 1; i <= 100; i++) {	
				tn.printB();
			//}
		}).start();
		
		new Thread(() ->{
			//for (int i = 1; i <= 100; i++) {	
				tn.printC();
			//}
		}).start();
	}
	
	/**
	 * 线程number--
	 */
	@Test
	public void testNumber(){
		
	}
	

	/**
	 * 线程锁测试
	 * @throws InterruptedException 
	 */
	@Test
	public void testLock() throws InterruptedException{
		ThreadLock t = new ThreadLock();
		
		/*Thread t1 = new Thread( () -> t.run(Thread.currentThread()));
		Thread t2 = new Thread( () -> t.run(Thread.currentThread()));
		t1.start();
		t2.start();*/
		
		Thread t1 =new Thread(new Runnable() {
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
		
		Thread t2 =new Thread(new Runnable() {
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
