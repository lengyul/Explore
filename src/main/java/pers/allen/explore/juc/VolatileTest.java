package pers.allen.explore.juc;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字：一种轻量级锁，用于保证在多线程之间修改共享变量的可见性
 * @author lengyul
 * volitile 不能保证原子性 不具备 （互斥性）
 * 内存可见性问题是 多线程操作共享数据时，（彼此不可见）每个线程中维护该变量的一个本地副本（缓存）
 * 使用场景（作为状态标记）：
 *  只有一个线程去更新变量
 *  变量会被多个线程读取
 * 	不需要加锁（加锁可以保证可见性）
 * 
 * 笔记：{@link #https://blog.csdn.net/qq_34808893/article/details/90546314}
 */
public class VolatileTest {
	
	/*
	 * 内存屏障和禁止重排序优化来实现
	 * 对volatile变量读操作时，读之前加入一条load屏障指令，从主内存中读取共享变量
	 * 对volatile变量写操作时，写之后加入一条store屏障指令，本地内存中共享变量值刷新到主内存
	 */
	

	public static void main(String[] args) throws InterruptedException {

		FlagClass fc = new FlagClass();

		new Thread(() -> {
			while (!fc.isFlag()) { // 读取flag的值为true时结束循环

			}
			System.out.println("---->end");
		}).start();

		TimeUnit.MILLISECONDS.sleep(200);

		fc.setFlag(true); // 修改flag的值为true
	}

	private static class FlagClass {

		private volatile boolean flag = false; // 变量声明为volatile类型

		public  boolean isFlag() {
			return flag;
		}

		public  void setFlag(boolean flag) {
			this.flag = flag;
		}
	}
	
	
}
