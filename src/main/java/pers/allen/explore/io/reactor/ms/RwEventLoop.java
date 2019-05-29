package pers.allen.explore.io.reactor.ms;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.CompletionHandler;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RwEventLoop extends Thread {

	private volatile Selector selector; // loop r/w
	private volatile SocketChannel socketChannel; // temp socketChannel
	private final CompletionHandler<ByteBuffer, SocketChannel> ch;// io handler

	@SuppressWarnings("unused")
	private volatile boolean interrupt = false;
	private final Queue<SocketChannel> registerEvent; // 注册读事件队列
	private final int bufferCapacity = 1024; // 默认缓冲区容量
	
	
	private final String prefix = this.getClass().getSimpleName(); // 线程名称
	private final int mark;

	public RwEventLoop(Selector selector, int ser) {
		this.selector = selector;
		this.mark = ++ser;
		this.setName(prefix + "-" + ser);
		this.registerEvent = new ConcurrentLinkedQueue<>();
		this.ch = new IoHandler();
	}

	/**
	 * socketChannel注册到Selector中
	 * @param socketChannel
	 */
	public void registerChannel(SocketChannel socketChannel) {
		if (socketChannel == null) {
			throw new NullPointerException("socketChannel");
		}
		try {
			socketChannel.configureBlocking(false);
			if (this.socketChannel == null) {
				this.socketChannel = socketChannel;
				this.start(); // 启动
			}
			if (registerEvent.offer(socketChannel)) { // 放入队列，由EventLoop线程run()方法取出注册
				selector.wakeup(); // 唤醒select方法	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 如果selector.select方法处于阻塞状态中，调用register方法的线程会被阻塞
	 * 仅在当前EventLoop线程中或配合wakeup方法来使用
	 * @param socketChannel
	 * @param ops
	 * @throws ClosedChannelException
	 */
	private void registerSelector(SocketChannel socketChannel, int ops) throws ClosedChannelException {
		socketChannel.register(selector, ops);
	}
	
	/**
	 * 使用标识保证register注册成功
	 * @param socketChannel
	 * @param ops
	 * @throws ClosedChannelException
	 */
	@SuppressWarnings("unused")
	private void wakeupAndRegister(SocketChannel socketChannel, int ops) throws ClosedChannelException {
		interrupt = true;
		selector.wakeup();
		socketChannel.register(selector, ops);
		interrupt = false;
	}

	/**
	 * 处理通道读写事件
	 */
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				while (!registerEvent.isEmpty()) {
					SocketChannel sc = registerEvent.poll();
					if (sc != null) {
						registerSelector(sc, SelectionKey.OP_READ);
					}
				}
				selector.select(); // blocking
				Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
				while (selectionKeys.hasNext()) {
					SelectionKey key = selectionKeys.next();
					selectionKeys.remove();
					if (key.isReadable()) {
						read((SocketChannel) key.channel());
					} else if (key.isWritable()) {
						write((SocketChannel) key.channel());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 读取当前通道中的数据并调用completed方法
	 * @param socketChannel
	 */
	private void read(final SocketChannel socketChannel) {
		ByteBuffer dst = ByteBuffer.allocate(bufferCapacity);
		try {
			int result = socketChannel.read(dst);
			if (result > 0) {
				registerSelector(socketChannel, SelectionKey.OP_READ);
				ch.completed(dst, socketChannel); // 数据处理
				// 一般不需要注册写操作，直接write即可
				//	registerSelector(socketChannel, SelectionKey.OP_WRITE);
			} else if(result < 0){
				socketChannel.close();
			}
		} catch (IOException e) {
			ch.failed(e, socketChannel); // 关闭socket，避免空轮询
		}
	}

	private void write(final SocketChannel socketChannel) {
		try {
			registerSelector(socketChannel, SelectionKey.OP_READ);
		} catch (ClosedChannelException e) {
			ch.failed(e, socketChannel);
		}
	}

}
