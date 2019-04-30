package pers.allen.explore.pattern.skeletal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class AbstractChannel implements Channel {
	
	// 由子类实例化时被赋值
	protected InputStream in; // 输入流
	protected OutputStream out; // 输出流
	
	protected AbstractChannel() {
	}
	
	// 子类具体去实现
	public abstract Class<?> getStreamSource();
	
	
	/**
	 * 基本字节传输方法
	 */
	@Override
	public int read(byte[] bytes) {
		try {
			return in.read(bytes);
		} catch (Exception e) {
			close();
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void write(byte[] bytes) {
		try {
			out.write(bytes);	
		} catch (Exception e) {
			close();
			e.printStackTrace();
		}
	}
	
	
	protected void setInOutStream(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}
	
	protected void setInOutStream(Socket socket) throws IOException {
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
	}
	
	/**
	 * 关闭流
	 */
	@Override
	public void close() {
		try {
			if (in !=null) {
				in.close();				
			}
			if (out != null) {
				out.close();				
			}
		} catch (Exception e) {
		}
	}
	
}
