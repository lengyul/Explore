package com.expolre.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import com.expolre.code.redis.RedisService;
import com.expolre.code.redis.RedisServiceImpl;

public class RedisClientTest {
	
	
	public static void main(String[] args) {
		RedisService rs = new RedisServiceImpl();
		
		rs.setKey("hello","world1");
		
		System.out.println(rs.getKey("hello"));
	}
	
	@Test
	public void test(){
		
		/*List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");*/
		
		RedisService rs = new RedisServiceImpl();
		//System.out.println(rs.setKey("aaa","123456"));
		/*System.out.println(rs.setKey("hello",Arrays.asList(0,1,2)));
		System.out.println(rs.setKey("hello",Arrays.asList(0,1,2)));
		System.out.println(rs.setKey("hello",Arrays.asList(0,1,2)));
		System.out.println(rs.setKey("hello",Arrays.asList(0,1,2)));*/
		
		
		System.out.println("-------------------------");
		/*System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("aaa"));*/
		/*System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("hello"));
		System.out.println(rs.getKey("hello"));*/
		
	}
	
}
