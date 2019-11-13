package pers.allen.explore.io.reactor.ms;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Reactor的代码实现依赖于NIO的Selector
 * @author lengyul
 * @date 2019年5月10日 下午4:06:51
 */
public class Reactor implements Runnable {

	/*
	 * 单Reactor单线程：单线程轮询Selector负责处理分发所有的I/O事件（accept、read、write）
	 * 	缺点：客户端的连接数过多，如果业务的处理比较耗时，会导致其他I/O事件阻塞在Selector不能被及时的轮询处理到
	 * 
	 * 单Reactor多线程：可以使用线程池进行改进，将I/O的读写事件直接分发到工作线程池中进行处理
	 * 
	 * 主从Reactor多线程：利用多核处理器的优势
	 * 	主：单线程处理 accept 事件，获取已连接的 socket ，绑定到 （会选取一个从反应堆线程） reactor 线程中
	 *  从：一组线程处理已连接的 socket 上的 I/O 事件
	 */
	@SuppressWarnings("unused")
	private final int port;
	private final ServerSocketChannel ssChannel;
	private Selector selector;
	
	public Reactor(int port) throws IOException {
		this.port = port;
		ssChannel = ServerSocketChannel.open(); // 打开通道
		selector =  Selector.open(); // 打开选择器
		ssChannel.socket().bind(new InetSocketAddress(port)); // 绑定端口
		ssChannel.configureBlocking(false); // 非阻塞
		SelectionKey sk = ssChannel.register(selector, SelectionKey.OP_ACCEPT); //注册选择器并接收连接事件
		sk.attach(new Acceptor());
	}
	
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				selector.select(); // blocking
				Iterator<SelectionKey> selectionKeys  = selector.selectedKeys().iterator();
				while (selectionKeys.hasNext()) {
					dispatch(selectionKeys.next());		
					selectionKeys.remove();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void dispatch(SelectionKey key) {
		Runnable r =  (Runnable) key.attachment(); // 获取初始化Reactor绑定的Acceptor
		if (r != null) {
			r.run();
		}
	}
	
	class Acceptor implements Runnable {
		
		EventLoopExecutor ele = null; // 事件执行器
		public Acceptor() throws IOException {
			ele = new EventLoopExecutor();
		}
		
		public void run() {
			try {
				SocketChannel sc = ssChannel.accept(); // 获取客户端
				if (sc != null) {
					ele.getRQueue().add(sc);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
