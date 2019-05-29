package pers.allen.explore.io.reactor.classic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classic ServerSocket Loop
 * @author lengyul
 * @date 2019年5月10日 上午11:51:43
 */
public class ClassicServerSocketLoop {

	/*
	 * 每一个请求（客户端连接）都会分配一个线程去处理
	 * 
	 * 
	 * client------------		   ---------  Thread(handler)
	 * 			 		  ________
	 * client------------|Server. |---------  Thread(handler)
	 * 					 |accept()|
	 * client------------		   ---------  Thread(handler)
	 * 
	 */
	
	class Server implements Runnable {

		final int port =  8888;
		
		@Override
		public void run() {
			try(ServerSocket ss = new ServerSocket(port)) {
				while (!Thread.interrupted()) {
				Socket socket = ss.accept(); // blocking waiting for socket connection
				new Thread(new Handler(socket)).start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	class Handler implements Runnable {
		Socket socket; // io channel
		InputStream in;  //  read bytes
		OutputStream out; // write bytes
		Handler(Socket socket) throws IOException { 
			if (socket != null) {
				this.socket = socket; 
				this.in = socket.getInputStream();
				this.out = socket.getOutputStream();
			}
		}
		
		@Override
		public void run() {
			byte[] ins = new byte[1024];
			try {
				this.in.read(ins); // blocking
				int result = process(ins); // mock 
				if (result == 1)
					out.write(ins); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private int process(byte[] bytes) {
			if (bytes != null) {
				return 1;				
			}
			return -1;
		}
	}
	
	
}
