package pers.allen.explore.pattern.decorator.io_uppercase;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputReadUpperCase extends FilterInputStream {

	protected InputReadUpperCase(InputStream in) {
		super(in);
		System.out.println("init\t"+this.getClass());
	}
	
	@Override
	public int read() throws IOException {
        return in.read();
    }	
	
	@Override
	public int read(byte b[], int off, int len) throws IOException {
		System.out.println("reading convert to uppercase");
		int result = in.read(b, off, len);
		for (int i = off; i < len; i++) {
			b[i] = (byte) Character.toUpperCase((char)b[i]); //字符转大写
		}
        return result;
    }
	

}
