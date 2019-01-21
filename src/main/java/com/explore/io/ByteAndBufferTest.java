package com.explore.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.junit.Test;


public class ByteAndBufferTest {
	
	@Test
	public void byetTest() throws IOException{
		
		//FileReader fr  = new FileReader(new File("C:\\Users\\Administrator\\Desktop\\工具.txt"));
		
		InputStream in = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\terminal.log"));
		
		byte [] bytes = new byte[in.available()];
		int len = in.read(bytes); // read(byte[] b): -1表示没有字节可用，0表示b的长度为0
		System.out.println("读取到字节数："+len);
		in.close();
		
		//获取换行符（enter） \r\n
		int sum = 0;
		for (int i = 0; i < bytes.length; i++) {
			byte b  = bytes[i];
			if (b == '\n') {
				sum+=1;
			}
		}
		System.out.println(sum);
		System.out.println(new String(bytes,Charset.forName("UTF-8")));
		
		/*Reader reader = new InputStreamReader(in);
		char [] c = new char[1024];
		reader.read(c);
		reader.close();
		System.out.println(new String(c));*/
	}
	
	
	@Test
	public void bufferTest(){		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//capacity = 1024;limit = 1024;position = 0;
		
		buffer.put("abc".getBytes());
		//capacity = 1024;limit = 1024;position = 3;
		
		buffer.flip();
		//capacity = 1024;limit = 3;position = 0;
		
		byte[] bytes = new byte[buffer.remaining()]; // limit - position = 3 - 0 = 3
		buffer.get(bytes);
		//capacity = 1024;limit = 3;position = 3;
		
		buffer.clear();
		//capacity = 1024;limit = 1024;position = 0;
		
		System.out.println(buffer.capacity());
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
	}

}
