package pers.allen.explore.test;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class JavaUnitTest {
	
	
	/*private static String zipRegEx = "^\\d{5}([\\-]\\d{4})?$";
	private static Pattern pattern;
	
	@BeforeClass //类中运行一次，public static 
	public static void setUpBeforeClass() {
		pattern = Pattern.compile(zipRegEx);
	}*/
	
	private String zipRegEx = "^\\d{5}([\\-]\\d{4})?$";
	private Pattern pattern;
	
	@Before //每个测试方法之前都执行一次，public
	public void setUpBefore() throws Exception {
		pattern = Pattern.compile(zipRegEx);
	}
	
	/*
	 * 异常测试
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void verifyZipCodeGroupException() {
		Matcher matcher =  pattern.matcher(zipRegEx);
		boolean isValid = matcher.matches();           
		matcher.group(2); 
	}
	
	
	/*
	 * 超时测试
	 */
	@Test(timeout = 1000)
	public void timeout() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
	}
	
	/*
	 * 忽略测试
	 */
	@Ignore
	@Test
	public void ignore() {
		
	}
	
}
