package pers.allen.explore.pattern.channel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class IoServiceImpl implements IoSerivce {

	InputStream in;

	OutputStream out;

	public IoServiceImpl() {}
	
	public IoServiceImpl(InputStream in,OutputStream out) {
		this.in = in;
		this.out = out;
	}

	public IoServiceImpl(Socket socket) {
		try {
			Objects.requireNonNull(socket);
			this.in = socket.getInputStream();
			this.out = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public void setOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public int read() throws IOException {

		return in.read();
	}

	@Override
	public int read(byte[] b) throws IOException {
		
		return in.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		
		return in.read(b, off, len);
	}

	@Override
	public void write(int b) throws IOException {
		
		out.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		
		out.write(b);
	}
	
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		
		out.write(b, off, len);
	}

}
