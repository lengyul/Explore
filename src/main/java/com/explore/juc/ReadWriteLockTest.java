package com.explore.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author lengyul
 *
 */
public class ReadWriteLockTest {
	
	public static void main(String[] args) {
		
		ReadWriteLockDemo rwld =new ReadWriteLockDemo();
		
		new Thread(() ->{
			rwld.write(5);
		}).start();
		
		for (int i = 0; i < 10; i++) {
			new Thread(() ->{
				rwld.read();
			}).start();
		}
		
	}
	
}
class ReadWriteLockDemo{
	
	private int number = 0;
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void read(){
		lock.readLock().lock(); //上锁
		try {			
			System.out.println(Thread.currentThread().getName()+":"+number);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.readLock().unlock();
		}
	}
	
	public void write(int number){
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName());
			this.number = number;			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.writeLock().unlock();
		}
	}
	
}
