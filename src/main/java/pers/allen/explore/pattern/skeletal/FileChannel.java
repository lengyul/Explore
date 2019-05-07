package pers.allen.explore.pattern.skeletal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileChannel extends AbstractChannel implements Channel {

	protected final File file;
	
	public FileChannel(String pathname) throws FileNotFoundException {
		file = new File(pathname);
		if (!file.exists()) {
			throw new FileNotFoundException(pathname);
		}
		in = new FileInputStream(file); 
		// 输入流未关闭，输出流操作同一文件，会造成冲突（文件内容会被清空），使用（RandomAccessFile）文件流
		out = new FileOutputStream(file); 
	}

	@Override
	public Class<?> getStreamSource() {
		return file.getClass();
	}

	
	

}
