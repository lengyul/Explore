package pers.allen.explore.io.reactor.ms;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EventLoopExecutor {
	
	private final int worker_count = Runtime.getRuntime().availableProcessors() * 2;
	private final Selector[] selectors = new Selector[worker_count];
	private final RwEventLoop[] eventloops = new RwEventLoop[worker_count];
	private volatile int next = 0;
	
	private final RegisterQueue RQ = new RegisterQueue();
	public RegisterQueue getRQ() {
		return RQ;
	}
	
	public EventLoopExecutor() throws IOException {
		for (int i = 0; i < worker_count; i++) {
			selectors[i] = Selector.open();
			eventloops[i] = new RwEventLoop(selectors[i],i);
		}
		new Thread(RQ).start(); // 启动注册队列线程
	}

	public RwEventLoop next(SocketChannel socketChannel) {
		if (next == worker_count) {
			next = 0;
		}
		eventloops[0].registerChannel(socketChannel);
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
