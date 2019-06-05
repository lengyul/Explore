package pers.allen.explore.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorConfig {

	// 核心线程数量
	private static final int corePoolSize;
	
	/*
	 *  最大线程数，如果队列已满，会创建新的线程（线程数量不会超过此参数的值）
	 *  tips：如果使用的是无界队列 @see LinkedBlockingQueue，此参数无效
	 */
	private static final int maximumPoolSize;
	
	// 线程空闲时间，单位 TimeUnit 指定
	private static final long keepAliveTime;
	
	private static final TimeUnit unit;
	
	// 阻塞队列（有界，无界）
	private static final int capacity;
	private static final BlockingQueue<Runnable> workQueue;
	
	// 线程工厂
	private static final ThreadFactory threadFactory;
	
	// 拒绝策略
	private static final RejectedExecutionHandler handler;
	
	static {
		/*
		 * 在不涉及 I/O 操作，设置线程数量为 Ncpu 或者 Ncpu + 1 为最优的吞吐量
		 * I/O 密集型可设置 Ncpu * 2
		 * 更多的线程并不会带来任何帮助，可能会降低性能，因为多个线程会在CPU和内存等资源上发生竞争
		 */
		corePoolSize = Runtime.getRuntime().availableProcessors();
		maximumPoolSize = corePoolSize;
		keepAliveTime = 10; unit = TimeUnit.MINUTES;
		capacity = 100000; workQueue = new ArrayBlockingQueue<>(capacity);
		threadFactory = new ThreadFactoryImpl();
		handler = new RejectedImpl();
	}
	
	private static ExecutorService worker_threads = 
			new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	
	public static Future<?> submit(Runnable task) {
		return worker_threads.submit(task);
	}
	
	public static void execute(Runnable r) {
		worker_threads.execute(r);
	}
	
	private static class ThreadFactoryImpl implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
		
        public ThreadFactoryImpl() {
        	group = Thread.currentThread().getThreadGroup();
        	namePrefix = "worker-thread-";
        }
		
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
			if (t.isDaemon())
				t.setDaemon(false);
			if (t.getPriority() != Thread.NORM_PRIORITY)
				t.setPriority(Thread.NORM_PRIORITY);
			return t;
		}
	}
	
	private static class RejectedImpl implements RejectedExecutionHandler {
		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			System.out.println("[ERROR]--> Runnable：" + r.toString() + "ThreadPoolExecutor："+ executor.toString());
		}
	}
	
}
