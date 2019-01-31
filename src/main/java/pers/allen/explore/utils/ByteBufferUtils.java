package pers.allen.explore.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * ByteBuffer转换工具类
 * 
 * @author lengyul
 *
 */
public class ByteBufferUtils {
	
	/**
	 * 将Bytebuffer中的字节转换为字符串
	 * 
	 * @param buffer
	 * @return
	 */
	public static String byteBufferToString(ByteBuffer buffer) {
		CharBuffer charBuffer = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder();
			charBuffer = decoder.decode(buffer);
			buffer.flip();
			return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
