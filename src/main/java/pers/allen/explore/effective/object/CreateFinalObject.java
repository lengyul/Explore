package pers.allen.explore.effective.object;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 避免创建不必要的对象（不可变对象）
 * @author lengyul
 * @date 2019年3月31日 下午3:12:05
 */
public class CreateFinalObject {
	
	/*
	 * String s = new String("allen");
	 * 该语句每次被执行的时候都创建一个新的String实例
	 * 
	 * String s = "allen";
	 * 这个版本只用了一个String实例，而不是每次执行的时候都创建一个新的实例，
	 * 它可以保证，同一台虚拟机运行的代码，只要它们包含相同的字符常量，该对象就会被重用
	 * 
	 * 优先使用静态工厂方法而不是构造器，以避免创建不必要的对象
	 * 静态工厂方法：Boolean.valueOf(b);
	 * 构造器：Boolean(value);在Java9中已经被废弃了，构造器在每次被调用的时候都会创建一个新的对象
	 */
	
	/*
	 * 另一种创建多余对象的方法，称作自动装箱（autoboxing）
	 * 它允许基本类型和装箱基本类型混用，按需要自动装箱和拆箱
	 * 结论：要优先使用基本类型而不是装箱基本类型
	 * Long sum = 0L; // 8秒，构造了大约2的31次方个多余的Long实例
	 * long sum = 0L; // 1秒
	 * 
	 */
	private static long sum() {
		Long sum = 0L; 
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		return sum;
	}
	
	/*
	 * Long 为不可变类，每次计算结果都会生成一个新对象
	 * Long i = 0L; // 8秒
	 * long sum = 0L; // 1秒
	 */
	private static void loop() {
		for (Long i = 0L; i < Integer.MAX_VALUE; i++) {}
	}
	
	public static void main(String[] args) {
		LocalDateTime start = LocalDateTime.now();
		loop();
		LocalDateTime end = LocalDateTime.now();
		Duration duration = Duration.between(start, end);
		System.out.println("耗时：" + duration.getSeconds() + "s");
	}
	
	
}
