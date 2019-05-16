package pers.allen.explore.thread.async;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.Callable;

public class FutureRead implements Callable<Integer> {

	private String pathname;
	private CompletionResult<Integer,ByteBuffer> cr;

	public FutureRead(String pathname, CompletionResult<Integer,ByteBuffer> cr) {
		this.pathname = pathname;
		this.cr = cr;
	}

	@Override
	public Integer call() throws Exception {

		return read(pathname, cr);
	}

	private static int read(String pathname, CompletionResult<Integer, ByteBuffer> cr) throws IOException {
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
				fc.close();
			}
			if (fin != null) {
				fin.close();
			}
		}
	}

}
