package com.expolre.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOddEvenNumber {

	public static void main(String[] args) throws InterruptedException {

		ThreadOddEvenNumberDemo tdend = new ThreadOddEvenNumberDemo();

		new Thread(tdend,"线程1").start();
		Thread.sleep(200);
		new Thread(tdend,"线程2").start();
	}
}

class ThreadOddEvenNumberDemo implements Runnable {
	
	private int number = 1;

	@Override
	public void run() {
		/*while (number <= 100) {
			synchronized (this) {
				try {					
					notify();
					System.out.println(Thread.currentThread().getName() + "：" + number++);
					wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}*/
		 synchronized (this) {
			try {				
				while (number <= 100) {
					notifyAll();
					System.out.println(Thread.currentThread().getName() + "：" + number++);
					wait();
				}
				notifyAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*private Lock lock = new ReentrantLock();
	private Condition con = lock.newCondition();
	
	@Override
	public void run() {
		try {
		lock.lock();
		while (number <= 100) {
				con.signal();
				System.out.println(Thread.currentThread().getName() + "：" + number++);
				con.await();
		}
		con.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}	
	}*/

}
