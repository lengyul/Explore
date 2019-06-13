package pers.allen.explore.thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 线程的取消与中断
 * @author lengyul
 * @date 2019年6月13日 下午4:37:43
 */
public class ThreadCancelAndInterrupt {

	public static void main(String[] args) throws InterruptedException, IOException {
		// 线程取消
		/*ThreadCancel tc = new ThreadCancel();
		new Thread(tc).start();
		TimeUnit.SECONDS.sleep(3);
		tc.cancel();*/ 
	
		// 线程中断（thread 阻塞方法）
		/*ThreadInterrupt ti = new ThreadInterrupt();
		ti.start();
		TimeUnit.SECONDS.sleep(3);
		ti.cancel();*/
		
		// 线程中断（socket 阻塞方法）
		/*final int port = 8889;
		ServerSocket ss = new ServerSocket(port);
		SocketThreadInterrupt sti = new SocketThreadInterrupt("localhost", port);
		new Thread(sti).start();
		TimeUnit.SECONDS.sleep(3);
		sti.cancel();*/
		
	}
	
	
	
	/**
	 * 线程取消（用于普通任务）
	 */
	private static class ThreadCancel implements Runnable {

		private volatile boolean canceled = false;
		
		@Override
		public void run() {
			while (!canceled) {}
		}
		
		@SuppressWarnings("unused")
		public void cancel() {
			canceled = true;
		}
	}
	
	/**
	 * 线程中断（用于抛出 InterruptedException 异常的阻塞方法）
	 * @see BlockingQueue
	 * @see Object.wait()、Thread.[sleep()、join()]
	 * ....
	 * @see ThreadInterruptAndJoin
	 */
	private static class ThreadInterrupt extends Thread {

		private final BlockingQueue<String> queue;
		
		public ThreadInterrupt() {
			queue = new ArrayBlockingQueue<>(1024);
		}

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					String str = queue.take(); // 阻塞直到队列中有数据
					System.out.println(str);
				} catch (InterruptedException e) {
					e.printStackTrace();
					// 恢复中断后继续执行
					// Thread.currentThread().interrupted();
				}
			}
		}

		@SuppressWarnings("unused")
		public void cancel() {
			interrupt(); // 中断线程
		}
	}
	
	/**
	 * 资源关闭（用于Socket、NIO 阻塞方法）
	 * @see Socket[read()、write()] 
	 * @see Selector.select()
	 */
	private static class SocketThreadInterrupt implements Runnable {

		private final Socket socket; 
		
		private final InputStream in;
		
		public SocketThreadInterrupt(String host, int port) throws UnknownHostException, IOException {
			socket = new Socket(host, port);
			in = socket.getInputStream();
		}
	
		@Override
		public void run() {
			try {
				byte[] bytes = new byte[1024];
				in.read(bytes); // 阻塞读取到可用数据
				System.out.println(new String(bytes));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 关闭流中断阻塞方法（java.net.SocketException）
		 * @throws IOException
		 */
		public void cancel() throws IOException {
			in.close();
			socket.close();
		}
	}
	
}
