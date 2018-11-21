package com.expolre.pattern.decorator.io_uppercase;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class ReadTest {

	@Test
	public void test() throws IOException {

		InputStream in = null;
		try {
			in = new InputReadUpperCase(new BufferedInputStream(new FileInputStream(
					"F:\\eclipse-mars\\eclipseWork\\Explore\\src\\main\\java\\com\\expolre\\pattern\\decorator\\io_uppercase\\hello.txt")));
			byte[] b = new byte[20];
			in.read(b);
			String str = new String(b);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}

	}
}
