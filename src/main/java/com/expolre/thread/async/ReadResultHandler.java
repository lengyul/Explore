package com.expolre.thread.async;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ReadResultHandler implements CompletionResult<Integer,ByteBuffer>{

	@Override
	public void completed(Integer result,ByteBuffer t) {
		if (result == -1) {
			return;
		}
		t.flip();
		byte [] bytes = new byte[t.remaining()];
		t.get(bytes);
		System.out.println("读取到数据："+new String(bytes,Charset.forName("UTF-8")));
	}

}
