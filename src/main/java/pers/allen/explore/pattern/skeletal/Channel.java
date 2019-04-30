package pers.allen.explore.pattern.skeletal;

import java.io.Closeable;

public interface Channel extends Closeable {

	int read(byte[] bytes);
	
	void write(byte[] bytes);
	
	Class<?> getStreamSource();
	
	default Class<?> getCurrentImplClass() {
		return this.getClass();
	}
	
}
