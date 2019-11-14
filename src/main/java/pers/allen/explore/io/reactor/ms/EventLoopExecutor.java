package pers.allen.explore.io.reactor.ms;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * sub-reactor 的线程列表
 * 每当有新的连接建立时，从 eventloops 中获取一个 event_loop 线程进行注册
 */
public class EventLoopExecutor {
	
	private final int worker_count = Runtime.getRuntime().availableProcessors() * 2;
	private final Selector[] selectors = new Selector[worker_count];
	private final EventLoop[] eventloops = new EventLoop[worker_count]; // sub-reactor threads
	private volatile int next = 0; // next event_loop_thread
	
	private final RegisterQueue rQueue = new RegisterQueue();
	public RegisterQueue getRQueue() {
		return rQueue;
	}
	
	public EventLoopExecutor() throws IOException {
		for (int i = 0; i < worker_count; i++) {
			selectors[i] = Selector.open();
			eventloops[i] = new EventLoop(selectors[i],i);
		}
		new Thread(rQueue).start(); // 启动注册队列线程
	}

	public EventLoop next(SocketChannel socketChannel) {
		if (next == worker_count) {
			next = 0;
		}
		eventloops[next].registerChannel(socketChannel);
		return eventloops[next++];
	}
	
	/**
	 * 注册队列
	 * @author lengyul
	 * @date 2019年5月13日 下午6:20:02
	 */
	class RegisterQueue implements Runnable {

		// 有界阻塞队列
		private BlockingQueue<SocketChannel> socketQueue = new ArrayBlockingQueue<>(1024);
		public RegisterQueue() {
		}

		public void add(SocketChannel sc) {
			socketQueue.add(sc);
		}

		public int size() {
			return socketQueue.size();
		}

		@Override
		public void run() {
		
			while (!Thread.interrupted()) {
				try {
					SocketChannel sc = socketQueue.take();
					next(sc); // 当前SocketChannel注册到下一个EventLoop
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
}
