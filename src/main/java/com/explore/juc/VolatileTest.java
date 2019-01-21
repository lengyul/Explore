package com.explore.juc;

/**
 * volatile关键字：多个线程在内存中操作数据时，直接在主存中操作
 * @author lengyul
 * 1.volitile 不具备“互斥性”
 * 2.volitile 不能保证变量的原子性
 * 内存可见性问题是多线程操作共享数据时，(彼此不可见)多个线程在内存中操作数据都有独立的缓存
 */
public class VolatileTest {
		
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();
		
		while (true) {
			if (td.isFlag()) {				
				System.out.println(Thread.currentThread().getName()+":"+td.isFlag());
				break;
			}
		}
		//加锁解决数据同步
		/*while (true) {
			synchronized (td) {
				if (td.isFlag()) {				
					System.out.println(Thread.currentThread().getName()+":"+td.isFlag());
					break;
				}	
			}
		}*/
	}
}

class ThreadDemo implements Runnable{
	
	private volatile boolean flag = false;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}



	@Override
	public void run() {
		try {
			Thread.sleep(500);
			flag = true;
			System.out.println(Thread.currentThread().getName()+":"+isFlag());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
