package pers.allen.explore.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pers.allen.explore.code.codec.UserInfo;
import pers.allen.explore.code.redis.RedisService;
import pers.allen.explore.code.redis.RedisServiceUtils;

public class RedisClientTest {
	
	
	public static void main(String[] args) {
		
		//new Thread(() -> System.out.println("Hello World!")).start();
		
		/*for (int i = 0; i < 100; i++) {
			boolean b = rs.setKey("hello"+i,"world"+i);
			System.out.println(b);
		}*/
		/*for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				boolean b = rs.setKey("hello5","world");
				System.out.println(b);
			}).start();
		}*/
		
		RedisService rs = new RedisServiceUtils();
				
		System.out.println(rs.setKey("user", new UserInfo("lengyul")));
		System.out.println(rs.getKey("user"));
		
	}
	
	
}
