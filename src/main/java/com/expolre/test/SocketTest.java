package com.expolre.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.junit.Test;

/**
 * Socket通讯
 * @author lengyul
 *
 */
public class SocketTest {

	@Test
	public void test() throws UnknownHostException, IOException{
		
		Socket socket =new Socket("127.0.0.1",10000);
		OutputStream os = socket.getOutputStream();
		InputStream  is =  socket.getInputStream();
		byte [] b = new byte[1024];
		Scanner sc =new Scanner(System.in);
		String str = sc.nextLine();
		//发送数据
		os.write(str.getBytes());
		//接收数据
		int n = is.read(b);
		System.out.println("服务端发送内容为："+new String(b, 0, n));
		
		os.close();
		is.close();
		socket.close();
	}
	
	
	@Test
	public void test2() throws IOException{
		ServerSocket serverSocket =new ServerSocket(10000);
		Socket socket =null;
		socket = serverSocket.accept();
		
		//初始化流
		OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        byte[] b = new byte[1024];
		
        int n = is.read(b);
        System.out.println("客户端发送内容为："+new String(b, 0, n));
        os.write("服务端响应成功".getBytes());
        
        os.close();
		is.close();
        socket.close();
        serverSocket.close();
	}
	
	
}
