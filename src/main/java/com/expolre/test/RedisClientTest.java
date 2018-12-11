package com.expolre.test;

import com.expolre.code.redis.RedisService;
import com.expolre.code.redis.RedisServiceImpl;

public class RedisClientTest {
	
	
	public static void main(String[] args) {
		
		//new Thread(() -> System.out.println("Hello World!")).start();
		
		
		RedisService rs = new RedisServiceImpl();
		//rs.setKey("hello","world1");
		
		//System.out.println(rs.getKey("hello"));
		//System.out.println(rs.getKey("hello"));
		
		for (int i = 0; i < 100; i++) {			
			new Thread(() -> System.out.println(rs.getKey("hello"))).start();
			System.out.println(rs.getKey("hello"));
		}
	}
	
	
}
