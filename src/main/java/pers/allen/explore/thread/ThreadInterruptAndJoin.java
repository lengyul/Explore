package pers.allen.explore.thread;

/**
 * 中断线程：线程的中断状态被设置
 * 当线程处于阻塞状态时(sleep、wait、join)，那么它的中断将被清除，并且会返回InterruptedException
 * @author lengyul
 * @date 2018年12月12日 下午4:09:45
 * 
 * 如果某个方法抛出 InterruptedException 时，表示该方法是一个阻塞方法
 * 中断是一种协调机制，一个线程不能强制其他线程停止正在执行的操作而去执行其他的操作
 */
public class ThreadInterruptAndJoin {
	public static void main(String[] args) throws InterruptedException{
		
		Thread t = new Thread(new ThreadInterrup());
		t.start();
		//t.interrupt();
		t.join(); //等待此线程执行完毕
		System.out.println("-------------------------end");
		
	}

	private static class ThreadInterrup implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
