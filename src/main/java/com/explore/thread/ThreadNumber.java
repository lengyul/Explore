package com.explore.thread;

public class ThreadNumber {

	public ThreadLocal<Integer> number = new ThreadLocal<>();
	private int flag = 1;

	public synchronized void printA() {
		try {
			for (int i = 1; i <= 100; i++) {
				while (flag != 1) {
					wait();
				}
				number.set(i);
				System.out.println(Thread.currentThread().getName() + "：" + number.get());
				flag = 2;
				notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void printB() {
		try {
			for (int i = 1; i <= 100; i++) {
				while (flag != 2) {
					wait();
				}
				number.set(i);
				System.out.println(Thread.currentThread().getName() + "：" + number.get());
				flag = 3;
				notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void printC() {
		try {
			for (int i = 1; i <= 100; i++) {
				while (flag != 3) {
					wait();
				}
				number.set(i);
				System.out.println(Thread.currentThread().getName() + "：" + number.get());
				flag = 1;
				notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
