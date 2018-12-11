package com.expolre.code.codec.protobuf;

import java.io.IOException;

import org.junit.Test;

import com.expolre.code.codec.protobuf.UserInfoProto.UserInfoReq;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Protobuf全称Google Protocol Buffer
 * @author lengyul
 * 它由谷歌开源而来，它将数据结构以.proto文件进行描述，通过代码生成工具可以生成对应的数据结构的POJO对象
 * 和Protobuf相关的方法和属性。
 * 它的特点如下：
 * 	结构化存储格式
 * 	高效的编解码能力
 * 	跨语言，平台无关，扩展性好
 *  官方支持Java，C++，和Python三种语言
 */
public class ProtobufTest {
	
	public static void main(String[] args) throws InvalidProtocolBufferException {
		UserInfoProto.UserInfoReq.Builder builder = UserInfoProto.UserInfoReq.newBuilder();
		builder.setUsername("Allen");
		builder.setUserID(10001);
		
		UserInfoReq req = builder.build();
		//encode
		byte[] bytes = req.toByteArray(); 
		System.out.println(bytes.length);
		
		//decode
		UserInfoReq rsp = UserInfoReq.parseFrom(bytes);
		System.out.println(rsp.toString());
		/*MsgReqProto.MsgReq.Builder builder = MsgReqProto.MsgReq.newBuilder();
		builder.setId(010110110);
		String value = "abc";
		builder.setData(value);
		builder.setLength(value.length());
		
		MsgReq req = builder.build();
		req.toByteArray();
		System.out.println("size:"+req.toByteArray().length);
		
		
		MsgReq rsp = MsgReq.parseFrom(req.toByteArray());
		System.out.println(rsp.toString());*/
	}
	
	@Test
	public void test(){
		
            String protoFile = "C:/Users/11749/Desktop/MsgReq.proto";//  
            //String strCmd = "C:/Users/Program/protobuf-master/protobuf-master/src/protoc.exe -I=C:/Users/11749/Desktop/Files --java_out=C:/Users/11749/Desktop/Files "+ protoFile;  
            String strCmd = "C:/Users/Program/protobuf-master/protobuf-master/src/protoc.exe -I=./proto --java_out=./src/main/java ./proto/"+ protoFile;  
            try {
                Runtime.getRuntime().exec(strCmd);
            } catch (IOException e) {
                e.printStackTrace();
            }//通过执行cmd命令调用protoc.exe程序  
        }
	
	
}
