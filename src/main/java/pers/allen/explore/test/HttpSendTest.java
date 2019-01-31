package pers.allen.explore.test;

import org.junit.Test;

import pers.allen.explore.utils.HttpClientUtils;

public class HttpSendTest {
		
	@Test
	public void test() throws Exception{
		
		String result = HttpClientUtils.HttpGet("http://www.baidu.com",null);
		System.err.println(result);
	}
	
}
