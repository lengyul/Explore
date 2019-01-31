package pers.allen.explore.thread;

import org.junit.Test;

/**
 * 
 * @author lengyul 
 * 在多线程环境，每个线程都会存有一个ThreadLocal的值，
 * 每个线程访问的get()都是自己调用set时备份的数据，而普通成员则被线程共享。
 */
public class ThreadLocalTest {
	// ThreadLocal这个类型，具有该类型的成员变量，每个线程都可以保留一份它的备份数据
	public ThreadLocal<String> threadLocal = new ThreadLocal<>();
	public String str = null;

	public class Threadl implements Runnable {

		// public String threadLocal = "";

		@Override
		public void run() {
			if (str == null) {
				str = "1";
				System.out.println("String:" + str);
			}
			if (threadLocal.get() == null) {
				threadLocal.set("1");
				System.out.println("threadLocal:" + threadLocal.get());
			}
		}
	}

	@Test
	public void test() {

		Thread t1 = new Thread(new Threadl());
		t1.start();

		Thread t2 = new Thread(new Threadl());
		t2.start();

	}

}
