package com.expolre.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class BlockingNIO {
	
	@Test
	public void client() throws IOException{
		
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));
		FileChannel inChannel = FileChannel.open(Paths.get("qq.jpg"),StandardOpenOption.READ);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (inChannel.read(buffer) != -1) {
			buffer.flip();
			socketChannel.write(buffer);
			buffer.clear();//清除缓冲区
		}

	//	socketChannel.shutdownOutput();//关闭输出流
		
		//接收服务端数据
		int len = 0;
		while ((len = socketChannel.read(buffer)) != -1) {
			buffer.flip();
			System.out.println("接收到服务端数据："+new String(buffer.array(),0,len));
			buffer.clear();
		}
		
		inChannel.close();
		socketChannel.close();
	}
	
	@Test
	public void server() throws IOException, InterruptedException{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		FileChannel outChannel = FileChannel .open(Paths.get("qq2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
		
		serverSocketChannel.bind(new InetSocketAddress(8888));
		SocketChannel socketChannel = serverSocketChannel.accept();
		//读取客户端数据
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (socketChannel.read(buffer) != -1) {
			buffer.flip();
			outChannel.write(buffer);
			buffer.clear();
		}
		
		buffer.put("服务端接收数据成功".getBytes());
		buffer.flip();
		socketChannel.write(buffer); //写入数据到客户端
		
		//关闭通道
		socketChannel.close();
		outChannel.close();
		serverSocketChannel.close();
	}
}
