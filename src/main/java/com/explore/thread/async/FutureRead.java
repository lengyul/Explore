package com.explore.thread.async;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.Callable;

public class FutureRead implements Callable<Integer> {

	private String pathname;
	private CompletionResult cr;

	public FutureRead(String pathname, CompletionResult cr) {
		this.pathname = pathname;
		this.cr = cr;
	}

	@Override
	public Integer call() throws Exception {

		return read(pathname, cr);
	}

	static int read(String pathname, CompletionResult<Integer, ByteBuffer> cr) {
		FileInputStream fin = null;
		FileChannel fc = null;
		try {
			fin = new FileInputStream(new File(pathname));// 获取文件输入流
			fc = fin.getChannel(); // 获取文件传输通道
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) fc.size());
			int result = fc.read(byteBuffer);
			cr.completed(result, byteBuffer); // 执行回调
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (fc != null) {
				try {
					fc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
