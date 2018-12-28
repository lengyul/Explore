package com.expolre.io.ochat;

public class ServerHandler implements Runnable {
	Client client;

	public ServerHandler(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		while (client.isRunner()) {
			String msg = client.receive();
			messageHandler(msg);
		}
	}

	public void messageHandler(String messgae) {
		if (messgae != null && "@:".equals(messgae.substring(0, ClientsInfo.SIGN_LENGTH))) {
			if (messgae.length() < ClientsInfo.SC_LENGTH) {
				this.client.send("[服务端]该客户端不存在：" + messgae.substring(ClientsInfo.SIGN_LENGTH, messgae.length()));
				return;
			}
			String clientId = messgae.substring(ClientsInfo.SIGN_LENGTH, ClientsInfo.SC_LENGTH);
			Client client = ClientsInfo.clients.get(clientId);
			if (client == null) {
				this.client.send("[服务端]该客户端不存在：" + clientId);
				return;
			}
			String msg = messgae.substring(ClientsInfo.SC_LENGTH, messgae.length());
			this.client.sendOthers(client, msg);
			return;
		}
		System.out.println("客户端[" + client.getClientId() + "]说：" + messgae);
	}
}
