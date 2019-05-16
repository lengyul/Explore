package pers.allen.explore.thread;

/**
 * Thread与Runnable的区别
 * @author lengyul
 * 1.Runnable是一个接口，Thread是一个类实现了Runnable接口
 * 2.Runnable接口可以避免java单继承带来的局限性
 * 3.Runnable可以被多个线程共享，适合多线程操作统一资源
 */
public class ThreadImpl {

	public static void main(String[] args) throws InterruptedException {

		RunnableApp mtd = new RunnableApp();
		Thread r1 = new Thread(mtd);
		Thread r2 = new Thread(mtd);
		Thread r3 = new Thread(mtd);
		r1.start();
		r2.start();
		r3.start();

		Thread.sleep(1000);
		System.out.println("------------------------------------");

		ThreadApp t = new ThreadApp();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();

		// 重复实例对象资源不会共享
		ThreadApp t1 = new ThreadApp();
		ThreadApp t2 = new ThreadApp();
		ThreadApp t3 = new ThreadApp();
		t1.start();
		t2.start();
		t3.start();
	}

	static class RunnableApp implements Runnable {
		private int number = 5;

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.println(number--);
			}
		}
	}

	static class ThreadApp extends Thread {

		private int number = 5;

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				System.out.println(number--);
			}
		}
	}
	
}
