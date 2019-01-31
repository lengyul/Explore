package pers.allen.explore.io.ochat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {

	public static void main(String[] args) {
		new ClientChat().start();
	}

	public void start() {
		Socket socket = null;
		Scanner sc = null;
		try {
			socket = new Socket("127.0.0.1", 8888);
			sc = new Scanner(System.in);
			Client client = new Client(socket);
			new Thread(new ClientHandler(client)).start();
			while (socket.isConnected()) {
				System.out.println("[you]请输入消息：");
				String msg = sc.nextLine();
				client.send(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sc != null) {
					sc.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
