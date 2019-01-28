package com.explore.pattern.aop;

import java.io.IOException;

public interface OutputService {
	
	void write(int b) throws IOException;
	
	void write(byte[] b) throws IOException;
	
	void write(byte b[], int off, int len) throws IOException;
}
