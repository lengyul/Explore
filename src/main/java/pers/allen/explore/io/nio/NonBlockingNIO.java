package pers.allen.explore.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import org.junit.Test;

/**
 * 使用NIO完成网络通信：
 * 1.通道Channel
 * 2.缓冲区Buffer
 * 3.选择器Selector：是selectableChannel的多路复用器
 * @author lengyul
 * 
 */
public class NonBlockingNIO {

	@Test
	public void client() throws IOException{
		SocketChannel socketChannel =SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));
		socketChannel.configureBlocking(false);//切换为非阻塞模式
		
		String res = "client--->data:你好！";
		byte [] bytes = res.getBytes();
		//写入数据到服务端
		ByteBuffer buffer =ByteBuffer.allocate(bytes.length);
		buffer.put(bytes);
		buffer.flip(); //数据传出状态
		socketChannel.write(buffer);
		buffer.clear();//清除缓冲区
		
		int len = 0;
		while (true) {
			    len =  socketChannel.read(buffer);
				if (len >  0 ) {	
					buffer.flip();
					/*byte [] bytes = new byte[len];
					buffer.get(bytes);*/
					System.out.println("接收到服务端数据："+new String(buffer.array(),0,len));
					buffer.clear();
				}
		}
		// socketChannel.close();
		
	}
	
	@Test
	public void server() throws IOException{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();	
		serverSocketChannel.configureBlocking(false);//切换为非阻塞模式
		serverSocketChannel.bind(new InetSocketAddress(8888));//监听端口8888
		//获取选择器
		Selector selector = Selector.open();
		//将通道注册到选择器，等待连接
		serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
		//获取选择器中已经准备就绪的事件
		while (selector.select() > 0) {
			//获取当前选择器所有注册的监听事件
			Iterator<SelectionKey> selectionKeys  = selector.selectedKeys().iterator();
			while (selectionKeys.hasNext()) {
				//获取事件	
				SelectionKey sk = selectionKeys.next();
				if (sk.isAcceptable()) {
					//获取客户端连接
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false); //切换非阻塞模式
					socketChannel.register(selector,SelectionKey.OP_READ);//注册选择器为读模式
				}else if(sk.isReadable()){ //(读就绪)有可读数据
					 SocketChannel socketChannel = (SocketChannel) sk.channel();
					 ByteBuffer buffer = ByteBuffer.allocate(1024);
					 int len = socketChannel.read(buffer);
					 if (len > 0 ) {						
						 System.out.println("接收到客户端数据："+new String(buffer.array(),0,len));
						 socketChannel.register(selector,SelectionKey.OP_WRITE);//注册选择器为写模式
					 }
				}else if(sk.isWritable()){ //(写就绪)可写数据，一般不需要去注册该(可写)事件，在读取数据后写入即可
					SocketChannel socketChannel = (SocketChannel) sk.channel();
					socketChannel.write(ByteBuffer.wrap("successful...".getBytes())); 
					socketChannel.register(selector,SelectionKey.OP_READ);//注册选择器为读模式
				}
				selectionKeys.remove(); //移除通道中的事件
			}
		}
		//关闭通道
		serverSocketChannel.close();
	}
	
	@Test
	public void server2() throws IOException{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();	
		serverSocketChannel.configureBlocking(false);//切换为非阻塞模式
		serverSocketChannel.bind(new InetSocketAddress(8888));//监听端口8888
		//获取选择器
		Selector selector = Selector.open();
		//将通道注册到选择器，等待连接
		serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
		//获取选择器中已经准备就绪的事件
		while (selector.select() > 0) {
			//获取当前选择器所有注册的监听事件
			Iterator<SelectionKey> selectionKeys  = selector.selectedKeys().iterator();
			while (selectionKeys.hasNext()) {
				//获取事件	
				SelectionKey sk = selectionKeys.next();
				if (sk.isAcceptable()) {
					//获取客户端连接
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false); //切换非阻塞模式
					socketChannel.register(selector,SelectionKey.OP_READ);//注册选择器为读模式
				}else if(sk.isReadable()){ //(读就绪)有可读数据
					new Thread(() -> {						
						//获取当前选择器上读就绪状态的通道						
						//读取客户端数据，这里省略
					}).start();	
				}else if(sk.isWritable()){ //(写就绪)可写数据，一般不需要去注册该(可写)事件，在读取数据后写入即可
					new Thread(() -> {						
						//获取当前选择器上写就绪状态的通道
						//写入数据到客户端，这里省略
					}).start();	
				}
				selectionKeys.remove(); //移除通道中的事件
			}
		}
		//关闭通道
		serverSocketChannel.close();
	}
	
	
}
