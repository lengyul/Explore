package com.expolre.thread;

/**
 * Thread与Runnable的区别
 * @author lengyul
 * 1.Runnable是一个接口，Thread是一个类实现了Runnable接口
 * 2.Runnable接口可以避免java单继承带来的局限性
 * 3.Runnable可以被多个线程共享，适合多线程操作统一资源
 */
public class ThreadImpl {
	
	public static void main(String[] args) throws InterruptedException {
		
		MyRunnableDemo mtd = new MyRunnableDemo();
		Thread r1 = new Thread(mtd);
		Thread r2 = new Thread(mtd);
		Thread r3 = new Thread(mtd);
		r1.start();
		r2.start();
		r3.start();
		
		Thread.sleep(1000);
		System.out.println("------------------------------------");
		
		
		MyThreadDemo t = new MyThreadDemo();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		
		//重复实例对象资源不会共享
		MyThreadDemo t1 = new MyThreadDemo();
		MyThreadDemo t2 = new MyThreadDemo();
		MyThreadDemo t3 = new MyThreadDemo();
		t1.start();
		t2.start();
		t3.start();
	}
	
}

class MyRunnableDemo implements Runnable{
	private int number = 5;
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(number--);
		}
	}
}


class MyThreadDemo extends Thread{
	
	private int number = 5;
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(number--);
		}
	}
	
}
