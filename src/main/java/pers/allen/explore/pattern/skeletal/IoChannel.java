package pers.allen.explore.pattern.skeletal;

import java.io.InputStream;
import java.io.OutputStream;

public class IoChannel extends AbstractChannel implements Channel {

	public IoChannel(InputStream in, OutputStream out) {
		if (in == null || out == null) {
			throw new NullPointerException();
		}
		setInOutStream(in, out);
	}
	
	
	@Override
	public Class<?> getStreamSource() {
		return BaseStream.class;
	}
	
	private static class BaseStream {
	}
	
}
