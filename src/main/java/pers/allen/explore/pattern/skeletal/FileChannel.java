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
		out = new FileOutputStream(file);
		in = new FileInputStream(file);
	}

	@Override
	public Class<?> getStreamSource() {
		return file.getClass();
	}

	
	

}
