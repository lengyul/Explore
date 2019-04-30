package pers.allen.explore.pattern.skeletal;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketChannel extends AbstractChannel implements Channel {

	protected final Socket socket;
	
	public SocketChannel(Socket s) throws IOException {
		if (s == null) {
			throw new NullPointerException();
		}
		socket = s;
		setInOutStream(socket);
	}
	
	public SocketChannel(InetAddress address, int port) throws IOException {
		socket = new Socket(address, port);
		setInOutStream(socket);
	}
	
	@Override
	public void close() {
		try {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Class<?> getStreamSource() {
		return socket.getClass();
	}


	

}
