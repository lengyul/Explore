package pers.allen.explore.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 异步非阻塞IO
 *  jdk1.7
 *  nio2.0
 * @author lengyul
 *
 */
public class NonBlockingAIO {
	
	Object obj  = new Object();
	
	@SuppressWarnings("unchecked")
	@Test
	public void client() throws IOException, InterruptedException, ExecutionException{
	    AsynchronousSocketChannel asChannel = AsynchronousSocketChannel.open();
	    /*
	     * 参数 
	     * remote - 要连接此通道的远程地址 
	     * attachment - 要附加到I / O操作的对象，可以是null 
	     * handler - 消耗结果的处理程序 
	     */
	    asChannel.connect(new InetSocketAddress("127.0.0.1",8888),null,new ClientConnectionHandler(asChannel));
	    synchronized (obj) {
	    	obj.wait();
		}
	}
	
	@Test
	public void server() throws IOException, InterruptedException, ExecutionException {
		/*
		 * 打开异步服务套接字通道 open() 打开异步服务器套接字通道 open(AsynchronousChannelGroup group)
		 * 指定异步通道组，用来"处理I/O事件"的线程池
		 */
		AsynchronousServerSocketChannel asSocketChannel = AsynchronousServerSocketChannel.open();
		asSocketChannel.bind(new InetSocketAddress(8888));// 监听端口8888
		while (true) {
			/*
			 * 接收客户端连接，使用Future方式： get方法是同步的 使用Future操作I /
			 * O(accept/read/write)会阻塞到操作完成或者超时异常，需要考虑操作场景
			 */
			Future<AsynchronousSocketChannel> future = asSocketChannel.accept();
			AsynchronousSocketChannel asChannel = future.get();
			System.out.println("============接收到客户端连接：" + asChannel.getRemoteAddress() + "==============");
			if (asChannel.isOpen()) { // 如果通道已经打开
				/*
				 * 接收客户端响应数据，Callback方式：发送I / O操作请求，指定一个(CompletionHandler
				 * handler) ，当异步I /
				 * O操作完成后，注册的handler的completed方法被调用，如果发送异常则调用failed方法。 dst -
				 * 将数据读取到readBuffer attachment - 要附加到I / O操作的对象 --> readBuffer
				 * handler - 读取客户端数据进行处理结果
				 */
				ByteBuffer readBuffer = ByteBuffer.allocate(20);
				asChannel.read(readBuffer, readBuffer, new ServerSocketChannelReadHandler(asChannel));
				// read方法不会阻塞下面的代码
				System.out.println(this.getClass() + "：" + Instant.now());
				// 
				/* 向客户端写入数据，Future方式
				 * Future<Integer> result = asChannel.write(ByteBuffer.wrap(
				 * "connecting successful!".getBytes())); if (result.get() == -1
				 * ) { //返回写入的字节数 return; }
				 */
			}
		}
	}
	
	
}
