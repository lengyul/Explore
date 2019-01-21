package com.explore.io.ochat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.explore.utils.RandomUtils;

public class ServerChat {

	public LinkedList<Socket> sockets = new LinkedList<>();
	ExecutorService socketPools = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		new ServerChat().start();
	}

	public void start() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8888);
			if (serverSocket != null) {
				System.out.println("服务端启动成功，监听地址为：" + serverSocket.getLocalSocketAddress());
				new Thread(new ServerListening()).start();
			}
			while (true) {
				Socket socket = serverSocket.accept();
				synchronized (sockets) {
					sockets.addLast(socket);
					sockets.notify();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class ServerListening implements Runnable {

		@Override
		public void run() {
			while (true) {
				if (sockets.isEmpty()) {
					synchronized (sockets) {
						try {
							sockets.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				addClient(); // 保存client
			}
		}

		public void addClient() {
			String clientId = RandomUtils.randomNumeric(ClientsInfo.CID_LENGTH);
			Client client = new Client(clientId, sockets.removeFirst());
			ClientsInfo.clients.put(clientId, client);
			socketPools.execute(new ServerHandler(client));
			// new Thread(new ServerHandler(client)).start();
			// ---------------------------------------------------------------------
			System.out.println("[Server]当前在线客户端人数：" + ClientsInfo.clients.size());
			System.out.print("ids[ ");
			ClientsInfo.clients.entrySet().stream().forEach((x) -> System.out.print(x.getKey() + " "));
			System.out.print("]");
			System.out.println("\n----------在线聊天室----------");
		}
	}

}
