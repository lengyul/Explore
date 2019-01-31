package pers.allen.explore.pattern.aop;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class IoServiceTest {

	@Test
	public void test() throws IOException {
		/*InputService readService = new IoServiceImpl();
		readService.read();
		readService.read(b);
		readService.read(b, off, len);

		OutputService writeService = new IoServiceImpl();
		writeService.write(b);*/

		IoSerivce ioSerivce = new IoServiceImpl();
		ioSerivce.setInputStream(new BufferedInputStream(new FileInputStream("qq.jpg")));
		

	}
}
