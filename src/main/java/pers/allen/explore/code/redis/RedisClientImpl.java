package pers.allen.explore.code.redis;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.commons.lang3.StringUtils;

public class RedisClientImpl implements RedisClient{
	
	private static RedisClient redisClient = null;
	private final String  address = "127.0.0.1"; //redis default address
	private final int  port = 6379; //redis default port
	private SocketChannel socketChannel = null;
	
	
	private RedisClientImpl(){
		init();
	}
	
	public void setSocketChannel(SocketChannel socketChannel) {
		if (socketChannel == null) {
			throw new NullPointerException("socketChannel");
		}
		if (this.socketChannel != null) {
			 throw new IllegalStateException("socketChannel set already");
		}
		this.socketChannel = socketChannel;
	}	
	
	public static RedisClient getInstance(){
		if (redisClient == null) {
			synchronized (RedisClientImpl.class) {
				if (redisClient == null) {
					redisClient = new RedisClientImpl();
				}
			}
		}
		return redisClient;
	}
	
	public void init(){
			init(address,port);
	}

	@Override
	public void init(String address,int port){
		SocketChannel socketChannel;
		try {
			socketChannel = SocketChannel.open(new InetSocketAddress(address,port));
			if (socketChannel.isConnected()) {
				this.socketChannel = socketChannel;	
				this.socketChannel.socket().setSoTimeout(2000);
				System.out.println("connect to "+address+":"+port+" success");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void isOpenChannel() {
			try {
				if (socketChannel == null)
				    throw new ConnectException("Connection not established or Channel not open");
			} catch (ConnectException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public ByteBuffer channelWriteBytes(RedisMsg msg) {
		try {
			String cmd = processMsgToCmd(msg);
			ByteBuffer byteBuffer = ByteBuffer.allocate(cmd.getBytes().length);
			if (StringUtils.isNotEmpty(cmd)) {	
				byteBuffer.put(cmd.getBytes());
				byteBuffer.flip();
				int result = socketChannel.write(byteBuffer);
				byteBuffer.clear();
				if (result == -1) {
					return null;
				}
			    ByteBuffer buffer = channelReadBytes();
			    return buffer; 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ByteBuffer channelReadBytes() {
		try {
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			int len = 0;
			while ((len = socketChannel.read(byteBuffer)) != -1) {
				byteBuffer.flip();
				return byteBuffer;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

		
	
	
	

	

}
