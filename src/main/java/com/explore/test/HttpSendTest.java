package com.explore.test;

import org.junit.Test;

import com.explore.utils.HttpClientUtils;

public class HttpSendTest {
		
	@Test
	public void test() throws Exception{
		
		String result = HttpClientUtils.HttpGet("http://www.baidu.com",null);
		System.err.println(result);
	}
	
}
