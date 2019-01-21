package com.explore.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

import org.junit.Test;

/**
 * 同步非阻塞
 * @author lengyul
 * @date 2019年1月16日 上午10:07:33
 * 新的输入/输出NIO是JDK1.4引入的。NIO弥补了原来的I/O的不足，它在标准Java代码中提供了高速、面向块的I/O。
 * 
 */
public class ReadmeNIO {

	// NIO 入门：https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
	
	/**
	 * 流与块的比较
	 * 面向流的I/O系统一次一个字节的处理。一个输入流产生一个字节的数据，一个输出流消费一个字节的数据。
	 * 面向块的I/O系统以块的形式处理数据。每一个操作都在一步中产生或者消费一个块。
	 * 
	 * 通道和缓冲区
	 * 通道和缓冲区是NIO中的核心对象，几乎每一个I/O操作都要使用它们。
	 * 通道是对原I/O包中的流的模拟。所有数据传输都必须通过一个Channel对象。
	 * Buffer实质上是一个容器对象，发送给一个通道的所有对象都必须首先放到缓冲区中，同样的，从通道中读取的任何数据都要
	 * 读到缓冲区。
	 *  
	 */
	@Test
	public void channelTest() throws IOException{
		//获取通道
		FileInputStream fin = new FileInputStream("qq.jpg");
		FileChannel fc = fin.getChannel();
		//创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//将数据读取到缓冲区
		fc.read(buffer);
		fc.close();
		fin.close();
	}
	
	/**
	 * 缓冲区内部细节：状态变量和访问方法
	 * 状态变量（缓冲区状态）：
	 * 		position：缓冲区读写位置，指定下一个字节在数组的位置。
	 *  	limit：缓冲区中的数据可读写空间，position总是小于或者等于limit。
	 *  	capacity：缓冲区可以存储的最大数据容量，limit决不能大于capacity。
	 *  	flip()：1.将limit设置为当前position 2.将position设置为0
	 *  	clear()：1.将limit设置为capacity   2.将position设置为0
	 *  	mark()：
	 *		reset()：
	 *		rewind()：
	 *  访问方法：
	 *  get()方法
	 *  	1.byte get();
	 *  	2.ByteBuffer get( byte dst[] );
	 *  	3.ByteBuffer get( byte dst[], int offset, int length );
	 *  	4.byte get( int index );
	 *  第一个方法读取单个字节 。第二个和第三个方法读到一个数组中。第四个方法从缓冲区的特定位置获取字节。
	 *  前三个get()方法是相对的，而最后一个方法是绝对的。相对意味着position在get之后会增加，绝对方法会忽略
	 *  limit和position的值，也不会影响它们。
	 *  
	 *  put()方法
	 *  	1.ByteBuffer put( byte b );
	 *  	2.ByteBuffer put( byte src[] );
	 *  	3.ByteBuffer put( byte src[], int offset, int lenght );
	 *  	4.ByteBuffer put( ByteBuffer src[] );
	 *  	5.ByteBuffer put( int index,byte b );
	 *  第一个方法写入单个字节。第二和第三个方法写入来自一个数组的一组字节。第四个方法将数据从一个给定的源
	 *  ByteBuffer写入这个ByteBuffer。第五个方法将字节写入缓冲区中特定的位置。
	 *  前四个put()方法是相对的，而第五个方法是绝对的。
	 *  	
	 */
	//缓冲区的访问方法
	@Test
	public void bufferTest(){
		ByteBuffer buffer = ByteBuffer.allocate(8);
		//capacity = 8;limit = 8;position = 0;
		
		buffer.get();buffer.get();buffer.get();
		//capacity = 8;limit = 8;position = 3;
		
		buffer.get();buffer.get();
		//capacity = 8;limit = 8;position = 5;
		
		buffer.flip();
		//capacity = 8;limit = 5;position = 0;
		
		byte [] bytes = new byte[4];
		buffer.put(bytes);
		//capacity = 8;limit = 5;position = 4;
		
		buffer.put((byte)0x01);
		//capacity = 8;limit = 5;position = 5;
		
		buffer.clear();
		//capacity = 8;limit = 8;position = 0;
		System.out.println(buffer.capacity());
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
	}
	//缓冲区的使用( clear()和flip() )
	@Test
	public void bufferUse() throws IOException{
		//用于让缓冲区在读和写之间切换
		SocketChannel socketChannel = null;
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (true) {
			buffer.clear();
			int r = socketChannel.read(buffer);
			if (r == -1) {// 没有字节可用
				break;
			}
			buffer.flip();
			socketChannel.write(buffer);
		}
		socketChannel.close();
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(0x03);
	}
	
}
