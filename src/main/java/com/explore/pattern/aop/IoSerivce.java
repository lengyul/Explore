package com.explore.pattern.aop;

import java.io.InputStream;
import java.io.OutputStream;

public interface IoSerivce extends InputService, OutputService {

	default <T> void isNotNull(Object... obj) {
		if (obj == null) {
			throw new NullPointerException("param is not null");
		}
		if (obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] == null) {
					throw new NullPointerException("param is not null");
				}
			}
		}
	}

	void setInputStream(InputStream in);

	void setOutputStream(OutputStream out);
}
