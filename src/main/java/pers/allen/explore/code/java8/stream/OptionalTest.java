package pers.allen.explore.code.java8.stream;

import java.util.Optional;

import org.junit.Test;

/**
 * Java8 Optional API
 * @author lengyul
 * @date 2019年4月16日 下午4:11:19
 * 
 * of()：指定一个非空的value，如果为空，抛出异常 NullPointerException
 * ofNullable()：指定一个value，可以为空
 * get()：获取value，如果存在值，则返回，否则抛出异常 NoSuchElementException
 * isPresent()：如果不为空，返回true
 * ifPresent()：如果值存在调用Consumer
 * orElse()：如果存在该值，返回，否则返回指定value
 * ....
 */
public class OptionalTest {

	@Test
	public void nullTest() {
		Object object = null;
		Optional<Object> optional = Optional.ofNullable(object);
		System.out.println(optional.isPresent());
		System.out.println(optional.orElse(1));
	}
		
	
	@Test
	public void nonNullTest() {
		Object object = new Object();
		Optional<Object> optional = Optional.of(object);
		optional.get(); 
		optional.ifPresent(System.out::println); // 如果不为空，do something
	}
		
		
}
