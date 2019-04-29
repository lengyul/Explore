package pers.allen.explore.thread.pool;

import java.util.LinkedList;

/**
 * 具有线程池的工作队列
 * 
 * @author lengyul
 * @date 2018年12月21日 上午9:37:50
 */
public class WorkQueue {

	@SuppressWarnings("unused")
	private final int nThreads; // 线程池大小
	private LinkedList<Runnable> queue; // 保存要执行线程的队列
	private PoolWorker[] poolWorkers; // 工作线程数组

	public WorkQueue(int nThreads) {
		this.nThreads = nThreads;
		queue = new LinkedList<>();
		poolWorkers = new PoolWorker[nThreads];
		for (int i = 0; i < nThreads; i++) {
			poolWorkers[i] = new PoolWorker();
			poolWorkers[i].start();// 启动工作线程
		}
	}

	public void execute(Runnable r) {
		synchronized (queue) {
			queue.addLast(r);
			queue.notify(); // 唤醒正在等待的工作线程，建议使用 notifyAll()
		}
	}

	private class PoolWorker extends Thread {
		@Override
		public void run() {
			Runnable r = null;
			while (true) {
				synchronized (queue) {
					while (queue.isEmpty()) { // 避免假唤醒
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					r = queue.removeFirst();
				}
				r.run(); // 执行完毕后再次轮询
			}
		}
	}
}
