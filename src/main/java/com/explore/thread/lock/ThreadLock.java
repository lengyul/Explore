package com.explore.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {

	private Lock lock = new ReentrantLock();

	// lock() 如果获取不到锁,就一直处于等待状态,直到拿到锁。
	// tryLock() 有一个返回值,如果拿不到锁直接返回false,停止等待。
	public void run(Thread thread) {
		lock.lock();
		try {
			System.out.println(thread.getName() + "获取锁成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock(); // 释放锁
			System.out.println(thread.getName() + "释放锁成功");
		}
	}

	public void runWaitTime(Thread thread) throws InterruptedException {
		if (lock.tryLock(5, TimeUnit.SECONDS)) { // 设置线程获取锁的等待时间5s
			try {
				System.out.println(thread.getName() + "获取锁成功");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock(); // 释放锁
				System.out.println(thread.getName() + "释放锁成功");
			}
		}
	}

}
