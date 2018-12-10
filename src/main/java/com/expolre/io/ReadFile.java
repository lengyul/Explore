package com.expolre.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


public class ReadFile {
	
	public static void main(String[] args) throws IOException {
		
		//FileReader fr  = new FileReader(new File("C:\\Users\\Administrator\\Desktop\\工具.txt"));
		
		InputStream in = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\CmdHandlerInitializer.java"));
		
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

}
