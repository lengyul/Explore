package pers.allen.explore.io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 处理客户端的连接
 * @author lengyul
 * @date 2018年11月20日 上午11:33:26
 */
public class ServerAccpetHandler implements CompletionHandler<AsynchronousSocketChannel,Void>{
	
	public ServerAccpetHandler() {
	}

	private AsynchronousServerSocketChannel asSocketChannel;

	public ServerAccpetHandler(AsynchronousServerSocketChannel  asSocketChannel) {
		this.asSocketChannel = asSocketChannel;
	}
	
	@Override
	public void completed(AsynchronousSocketChannel asChannel, Void attachment) {
		try {
			System.out.println("-----------客户端"+asChannel.getRemoteAddress()+"建立连接...-------------");
			asChannel.write(ByteBuffer.wrap("connecting successful!".getBytes()));//写入数据到客户端
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void failed(Throwable exc, Void attachment) {
		System.out.println("-------接收客户端连接时发生异常------");
	}


	

	

}
