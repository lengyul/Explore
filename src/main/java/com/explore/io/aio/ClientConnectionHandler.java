package com.explore.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 异步通道允许指定完成处理程序以消耗异步操作的结果
 * 
 * @author lengyul
 *
 */
public class ClientConnectionHandler implements CompletionHandler<Void, ByteBuffer> {

	public ClientConnectionHandler() {
	}

	private AsynchronousSocketChannel asChannel;

	public ClientConnectionHandler(AsynchronousSocketChannel asChannel) {
		this.asChannel = asChannel;
	}

	/*
	 * 当I / O操作成功完成时，将调用completed方法。 (non-Javadoc)
	 * 
	 * @see java.nio.channels.CompletionHandler#completed(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void completed(Void result, ByteBuffer attachment) {
		System.out.println("客户端连接服务端成功！");
		attachment = ByteBuffer.allocate(1024);
		try {
			asChannel.read(attachment).get();
			attachment.flip();
			System.out.println("接收到服务端数据：" + new String(attachment.array()));
			//向当前连接服务端发送数据
			asChannel.write(ByteBuffer.wrap("The client is ready...".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * System.out.println(result); System.out.println(attachment);
		 * //附加到IO的对象
		 */
	}

	/*
	 * 如果I / O操作失败，则调用failed方法。 (non-Javadoc)
	 * 
	 * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable,
	 * java.lang.Object)
	 */
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub

	}

}
