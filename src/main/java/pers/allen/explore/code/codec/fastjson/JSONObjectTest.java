package pers.allen.explore.code.codec.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import pers.allen.explore.code.codec.UserInfo;

public class JSONObjectTest {

	public static void main(String[] args) {
		
		UserInfo userInfo = new UserInfo("Allen",10001); // 5 + 4 + 4 
		byte[] bytes = JSON.toJSONBytes(userInfo);
		System.out.println(bytes.length);
		
		UserInfo userInfo2 = JSON.parseObject(bytes, UserInfo.class);
		System.out.println(userInfo2.toString());
	}
}
