package com.expolre.code.codec.msgpack;

import java.io.IOException;

import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;
import com.expolre.code.codec.UserInfo;

/**
 * MessagePack
 * @author lengyul
 * @date 2018年12月11日 上午9:27:52
 * MessagePack是一个高效的二进制序列化格式，它让你像JSON一样可以在各种语言之间交换数据。
 * It’s like JSON.
 * but fast and small.
 * 与protobuf相比之下有这些优点：
 * 	 兼容json的数据格式
 *   比json的序列化更省时间和空间
 *   支持多种语言
 */
public class MessagePackTest {
	
	public static void main(String[] args) throws IOException {
		UserInfo userInfo = new UserInfo("Allen",001); //5 + 5 + 3

		//Serialize with MessagePacker
		MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
		packer.packInt(userInfo.getUserID())
			  .packString(userInfo.getUsername())
			  .packArrayHeader(2)
			  .packString("x")
			  .packString("y");
		packer.close();
		//Deserialize with MessagePacker
		MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(packer.toByteArray());
		int id = unpacker.unpackInt();
		String usernmae = unpacker.unpackString();
		int nums = unpacker.unpackArrayHeader();
		String[] strs = new String[nums];
		for (int i = 0; i < nums; i++) {
			strs[i] = unpacker.unpackString(); 
		}
		unpacker.close();
		System.out.println(String.format("id:%d, name:%s, strs:[%s]", id, usernmae, join(strs)));
	}
	
	
	private static String join(String[] in)
    {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < in.length; ++i) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(in[i]);
        }
        return s.toString();
    }
	
}
