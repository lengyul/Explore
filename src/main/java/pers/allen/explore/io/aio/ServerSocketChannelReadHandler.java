package pers.allen.explore.io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * 读取客户端数据处理
 * @author lengyul
 * @date 2018年11月20日 下午2:52:21
 */
public class ServerSocketChannelReadHandler implements CompletionHandler<Integer,ByteBuffer>{
	
	private AsynchronousSocketChannel asChannel;
	
	public ServerSocketChannelReadHandler(AsynchronousSocketChannel asChannel){
		this.asChannel = asChannel;
	}
	
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		// System.out.println(result+","+attachment);
		if (result == -1) { //客户端停止套接字传输
			try {
				this.asChannel.close();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			 TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//读取字节数据
		attachment.flip();
        System.out.println("接收到客户端响应数据： " + Charset.forName("UTF-8").decode(attachment));
        attachment.clear();
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		System.out.println("---------------服务端接收数据异常，关闭当前通道连接----------------");
		System.out.println(exc.getMessage());
		try {
			this.asChannel.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

}
