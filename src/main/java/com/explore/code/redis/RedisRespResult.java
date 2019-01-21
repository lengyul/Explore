package com.explore.code.redis;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

import org.apache.commons.lang3.StringUtils;
public class RedisRespResult {
	

	private RedisRespResult(){}
	
	private static RedisClient redisClient = RedisClientImpl.getInstance();
	
	
	public static boolean set(RedisMsg msg) {
		String res = sendCommand(msg);
		if ("OK".equals(res)) {
			return true;
		}
		return false;
	}

	public static String get(RedisMsg msg) {
		if (StringUtils.isEmpty(msg.getKey())) {
			throw new NullPointerException("key is not null");
		}
		return sendCommand(msg);
	}

	
	private static String sendCommand(RedisMsg msg){
		ByteBuffer byteBuffer = redisClient.channelWriteBytes(msg);
		return analysisResult(byteBuffer);
	}
	
	/**
	 * 解析server返回的数据
	 * @param byteBuffer
	 * @return
	 */
	private static String analysisResult(ByteBuffer byteBuffer){
		if (byteBuffer.limit() == 0) {
			throw new BufferOverflowException();
		}
		// byteBuffer.flip();
		String res = null;
		byte head = (byte) byteBuffer.get(); // + == set result , $ == get
		char type = (char) head;
		if (type == '+') {
			byte[] dst = new byte[2];
			byteBuffer.get(dst);
			res = new String(dst);
		} else if (type == '$') {	//返回为字符串
			Integer len = readDataLength(byteBuffer); //value.length
			if (len == -1) {
				return null;
			}
			/*byteBuffer.get();// CR
			byteBuffer.get();// LF*/			
			byte[] dst = new byte[len];
			byteBuffer.get(dst);
			res = new String(dst);
		}
		return res;
	}
	
	
	/**
	 * 读取数据长度
	 * @param buffer
	 * @return
	 */
    private static Integer readDataLength(ByteBuffer buffer){
    	//在此之前，buffer已经读取了一个字节（head）
        byte b = (byte)buffer.get();
        StringBuffer sb = new StringBuffer();
        try {
        //不是最后一个输入字节时
        while(b != -1){
            //判断是否是CR,如果不是加入sb中
            if(b != '\r'){
                sb.append((char)b);
            }else{
                //如果是CR,继续读取一个字节,如果不是LF,报错
                byte oneMore = (byte)buffer.get();
                if(oneMore != '\n'){
                    throw new RuntimeException("CRLF error!");
                }else{
                    break;
                }
            }
            b = (byte)buffer.get();
        }
        	} catch (Exception e) {
			e.printStackTrace();
        	}
        return Integer.parseInt(sb.toString());
    }
	
	
	
	public static void main(String[] args) {
		/*
		 * RedisMsg msg = new RedisMsg(); msg.setCmd(RedisCmd.GET);
		 * msg.setKey("hello"); RedisRespResult.get(msg);
		 */
	}

}
