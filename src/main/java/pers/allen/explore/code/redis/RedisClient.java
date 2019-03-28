package pers.allen.explore.code.redis;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Map.Entry;

public interface RedisClient {
	/**
	 * 将消息转换为命令
	 * @param msg
	 * @return
	 */
	default String processMsgToCmd(RedisMsg msg) {
		isOpenChannel();
		StringBuffer sb = new StringBuffer();
		int size = 0;
		RedisCmd cmd = msg.getCmd();
		
		switch (cmd) {
		case GET:
			//size = dataMap.size() + 1;
			size = 2;
			sb.append("*" + size + msg.CRLF + "$3" + msg.CRLF + "GET");
				String gkey = msg.getKey();
				sb.append(msg.CRLF + "$" + gkey.length() + msg.CRLF + gkey + msg.CRLF);
			break;
		case SET:
			Map<String, Object> dataMap = msg.getDataMap();
			size = dataMap.size() + 2;
			sb.append("*" + size + msg.CRLF + "$3" + msg.CRLF + "SET");
			if (dataMap.size() > 0) {			
				for (Entry<String, Object> str : dataMap.entrySet()) {
					String skey = str.getKey().toString();
					String value = str.getValue().toString();
					sb.append(msg.CRLF + "$" + skey.length() + msg.CRLF + skey + msg.CRLF + "$" + value.length() + msg.CRLF
							+ value + msg.CRLF);
				}
			}
			break;
		default:
			break;	
		}
		return sb.toString();
	}
	
	
	/**
	 * 指定连接Redis地址和端口
	 * @param address
	 * @param port
	 */
	void init(String address,int port);
		
	/**
	 * 通道是否打开
	 */
	void isOpenChannel();
	
	/**
	 * 写入字节数据（set）
	 * @param msg
	 * @return
	 */
	ByteBuffer channelWriteBytes(RedisMsg msg);
	
	/**
	 * 读取返回字节数据（cmd）
	 * @return
	 */
	ByteBuffer channelReadBytes();
	
}
