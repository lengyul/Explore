package pers.allen.explore.juc;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字：一种轻量级锁，用于保证在多线程之间修改共享变量的可见性
 * @author lengyul
 * volitile 不能保证原子性 不具备 （互斥性）
 * 内存可见性问题是多线程操作共享数据时，（彼此不可见）多个线程在内存中操作数据都有独立的缓存（工作内存）
 * 使用场景（作为状态标记）：
 *  只有一个线程去更新变量
 *  变量会被多个线程读取
 * 	不需要加锁（加锁可以保证可见性）
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
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		while (true) {
			/*
			 * synchronized (anything) { //一个线程修改的数据对于其他线程是可见的 }
			 */
			if (flag) {
				System.out.println(Thread.currentThread().getName() + "：" + flag);
				break;
			}
		}

	}
}
