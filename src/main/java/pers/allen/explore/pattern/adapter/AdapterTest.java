package pers.allen.explore.pattern.adapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.junit.Test;


/**
 * 适配器就是一种适配中间件，它存在于不匹配的二者之间，用于连接二者，将不匹配变得匹配，
 * 简单点理解就是平常所见的转接头，转换器之类的存在。
　　适配器模式有两种：类适配器、对象适配器、接口适配器
 * @author lengyul
 *
 */
public class AdapterTest {

	@Test
	public void test() throws IOException{
		
		OutputService ops =new OutputAdapterImpl();
		ops.print("allen");
		
		//InputStream 原角色 , InputStreamReader适配器  , Reader目标接口
		InputStream is = new FileInputStream("C:\\Users\\Administrator\\Desktop\\security.txt");
		Reader reader = new InputStreamReader(is);
		
		char [] c =new char[2048];
		reader.read(c);
		String str = new String(c);
		System.out.println(str);
		reader.close();
		
		/*byte [] b =new byte[1024];
		while (is.read()!=-1) {
			is.read( );
		}
		ByteBuffer buffer =ByteBuffer.wrap(b);
		System.out.println(buffer.get(0));
		is.close();*/
	}
	
			
}
