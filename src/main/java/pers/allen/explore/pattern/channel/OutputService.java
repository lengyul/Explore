package pers.allen.explore.pattern.channel;

import java.io.IOException;

public interface OutputService {
	
	void write(int b) throws IOException;
	
	void write(byte[] b) throws IOException;
	
	void write(byte b[], int off, int len) throws IOException;
}
