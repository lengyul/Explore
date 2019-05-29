package pers.allen.explore.io.reactor.ms;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.channels.SocketChannel;

public class IoHandler implements CompletionHandler<ByteBuffer, SocketChannel>{

	@Override
	public void completed(ByteBuffer data, SocketChannel attachment) {
		data.flip();
		int len = data.remaining();
		System.out.println(Thread.currentThread().getName()+ "：" + new String(data.array(),0,len));
		data.clear();
		try {
			attachment.write(data.put("server ok".getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void failed(Throwable exc, SocketChannel attachment) {
		try {
			attachment.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	
}
