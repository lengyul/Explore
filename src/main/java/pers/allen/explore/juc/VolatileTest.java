package pers.allen.explore.juc;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字：多个线程在内存中操作数据时，直接在主存中操作
 * @author lengyul
 * 1.volitile 不具备 互斥性
 * 2.volitile 不能保证变量的原子性
 * 内存可见性问题是多线程操作共享数据时，（彼此不可见）多个线程在内存中操作数据都有独立的缓存（工作内存）
 */
public class VolatileTest {
	
	/*
	 * 内存屏障和禁止重排序优化来实现
	 * 对volatile变量读操作时，读之前加入一条load屏障指令，从主内存中读取共享变量
	 * 对volatile变量写操作时，写之后加入一条store屏障指令，本地内存中共享变量值刷新到主内存
	 */
	private volatile static boolean flag = false;
	
	public static void main(String[] args) {
		
		new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(200);
				flag = true;
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		while (true) {
			/*synchronized (anything) {
				//一个线程修改的数据对于其他线程是可见的
			}*/
			if (flag) {		
				System.out.println(Thread.currentThread().getName()+"："+flag);
				break;
			}
		}
		
	}
	
	
}
