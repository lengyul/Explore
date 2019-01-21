package com.explore.io.ochat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	private String clientId;
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	private boolean runner = false;

	public Client(Socket socket) {
		init(socket);
	}

	public Client(String clientId, Socket socket) {
		init(clientId, socket);
	}

	public void init(Socket socket) {
		init(null, socket);
	}

	public void init(String clientId, Socket socket) {
		this.clientId = clientId;
		this.socket = socket;
		if (this.socket != null) {
			try {
				runner = true;
				in = socket.getInputStream();
				out = socket.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isRunner() {
		return runner;
	}

	public boolean isOpen() {

		return this.socket.isConnected();
	}

	public String getClientId() {
		return clientId;
	}

	public void send(String messgae) {
		try {
			out.write(messgae.getBytes());
		} catch (IOException e) {
			runner = false;
			try {
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void sendOthers(Client client, String messgae) {
		try {
			messgae = "客户端[" + client.getClientId() + "]对你说：" + messgae;
			client.out.write(messgae.getBytes());
		} catch (IOException e) {
			runner = false;
			try {
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public String receive() {
		String data = null;
		byte[] bytes = null;
		try {
			int len = in.available();
			if (len == 0) {// bytes.length为0时，read方法则不会读取字节并返回0
				bytes = new byte[1024];
			} else {
				bytes = new byte[len];
			}
			len = in.read(bytes);
			/*
			 * if (bytes[]) {
			 * 
			 * }
			 */
			data = new String(bytes, 0, len);
			return data;
		} catch (IOException e) {
			runner = false;
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		}
	}

}
