package com.expolre.test;

import org.junit.Test;

import com.expolre.util.DateTimeUtils;

public class DateTimeTest {
		
	@Test
	public void test(){
		System.out.println(DateTimeUtils.getNowTime());
		System.out.println(DateTimeUtils.getUTCNowTime());
		System.out.println(DateTimeUtils.getNowTime(DateTimeUtils.DTF_DATE_YMD));
		System.out.println(DateTimeUtils.getNowTime(DateTimeUtils.DTF_DATE_YMDHMS));
		System.out.println(DateTimeUtils.getNowTimeByZone("Asia/Shanghai"));
		System.out.println(DateTimeUtils.getPlusDayTime(100));
		
	}
}
