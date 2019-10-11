package pers.allen.explore.io.reactor.ms;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class ReactorTest {

	private static final String IP = "127.0.0.1";
	private static final int PORT = 8888;
	private static final CountDownLatch cdl = new CountDownLatch(1);

	public static void main(String[] args) throws IOException, InterruptedException {

		new Thread(new Reactor(PORT)).start();

	}

	@Test
	public void test() throws UnknownHostException, IOException, InterruptedException {
		mockSocket(2000);
		cdl.await();
	}

	private static void send() {
		Socket socket = null;
		try {
			socket = new Socket(IP, PORT);
			OutputStream out = socket.getOutputStream();
			String data = "the socket number：" + socket.toString();
			out.write(data.getBytes());
			cdl.await();
			/*for (int j = 0; j < 5; j++) {
				TimeUnit.SECONDS.sleep(2);
				out.write(data.getBytes());
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void mockSocket(int count) throws UnknownHostException, IOException {
		for (int i = 1; i <= count; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					send();
				}
			}).start();
		}
	}

}
