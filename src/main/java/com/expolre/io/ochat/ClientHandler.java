package com.expolre.io.ochat;

import org.apache.commons.lang3.StringUtils;

public class ClientHandler implements Runnable {

	Client client;

	public ClientHandler(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		while (client.isRunner()) {
			String msg = client.receive();
			if (StringUtils.isNotEmpty(msg)) {
				System.out.println("[you]" + msg);
			}
		}
	}

}
