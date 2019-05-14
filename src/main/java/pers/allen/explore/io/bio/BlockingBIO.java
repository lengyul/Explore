package pers.allen.explore.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 同步阻塞式
 * 1.ServerSocket Socket
 * 2.InputStream OutputStream
 * @author lengyul
 * 
 */
public class BlockingBIO {
	
	@Test
	public void client() throws UnknownHostException, IOException{
		
		Socket socket = new Socket("127.0.0.1", 8888);
		
		OutputStream out = socket.getOutputStream();
		out.write("abcdefg".getBytes());
		
		// socket.shutdownOutput();
		InputStream in = socket.getInputStream();
		int len = 0;
		byte [] b = new byte[1024];
		while ((len = in.read(b)) != -1) {
			System.out.println("接收到服务端数据："+new String(b, 0, len));
			break;
		}
		out.close();
		in.close();
		socket.close();
	}
	
	
	@Test
	public void server() throws IOException{
		
		ServerSocket serverSocket = new ServerSocket(8888); // 监听端口8888
		try {
			while (true) {
				Socket socket = serverSocket.accept(); //阻塞式接收socket连接
				new Thread(() -> {  //创建一个新的线程
					byte[] b = new byte[1024];
					try {
						InputStream in = socket.getInputStream();
						int len = in.read(b); // 读取客户端发送的数据
						System.out.println("接收到客户端数据：" + new String(b,0,len));
						OutputStream out = socket.getOutputStream();
						out.write("successful...".getBytes()); // 返回数据到客户端
						//关闭连接
						in.close(); out.close(); socket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
		
	}
	
	
	
}