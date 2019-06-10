package pers.allen.explore.effective.core;

import java.util.Comparator;
import java.util.List;


/**
 * Lambda 优先于匿名类
 * @author lengyul
 * @date 2019年6月5日 下午7:43:12
 */
public class Lambda {

	
	/*
	 * 在以往对接口创建实例的主要方式是通过 匿名类（anonymous class）
	 */
	private List<String> list = null;
	// 对 list 进行排序
	public void test() {
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Integer.compare(o1.length(), o2.length());
			}
		});
	}
	
	/*
	 * 	在 Java 8 中，出现了 带有单个抽象方法的接口，这些接口现在被称作函数接口（functional interface），
	 * Java 允许利用 Lambda 表达式（Lambda expression）创建这些接口的实例
	 * Lambda 类似于匿名类的函数（语法糖），但是比它简洁的多
	 * 
	 */
	public void test2() {
		/**
		 * 从匿名类到Lambda 表达式的转换
		 */
		list.sort((o1,o2) -> Integer.compare(o1.length(), o2.length()));
	}
	
	/*
	 * 简短的 Lambda 表达式能提高代码可读性，这是函数式编程的重要好处之一
	 * 
	 * tips：
	 * Lambda 没有名称和文档；如果一个计算本身不是自描述的，或者超出了几行，就不要把它放在一个 Lambda 中
	 * 对于 Lambda 而言，一行是最理想的，三行是合理的最大极限；包含多行的 Lambda 表达式会让代码变得杂乱且难以阅读
	 * 
	 * Lambda 中无法获得对自身的引用（this），Lambda 中引用的局部变量必须是声明为 final类型
	 */
	
	/**
	 * 方法引用优先于 Lambda
	 * @author lengyul
	 * @date 2019年6月5日 下午8:06:35
	 */
	@SuppressWarnings("unused")
	private class MethodReference {
		/*
		 * Java 提供了生成比 Lambda 更简洁函数对象的方法：方法引用
		 * 对于 Lambda 中的传递一个或者多个形参，最好将它替换为方法引用，使用 :: 来调用 ，无论是传递给静态方法还是实例方法
		 * 
		 */
		public void test() {
			/**
			 * 从Lambda 表达式到方法引用的转换
			 */
			list.sort(Comparator.comparingInt(String::length)); // str.length()
		}
		
		public void test2() { // list中的每个元素大写
			list.stream().map((e) -> e.toUpperCase()); // lambda
			list.stream().map(String::toUpperCase); // 方法引用
		}
		
		/**
		 * 
		 * 五种方法引用
		 * ______________________________________________________________________
		 *|	 方法引用类型			范例			 			Lambda 等式				 |
		 *|   静态			Integer::parseInt	 	str -> Integer.parseInt(str) |
		 *|   有限制			Instant.now()::isAfter	Instant then = Instant.now();t -> then.isAfter();
		 *    无限制			String::toLowerCase		str -> str.toLowerCase()
		 *|	    类构造器		String::new				() -> new String()			 |	
		 *|   数组构造器 		int[] ::new 			len -> new int[len]			 |
		 *|______________________________________________________________________|	 
		 */
		
		/*
		 * 只要方法引用更加简洁，清晰，就用方法引用；如果方法引用并不简洁，就坚持使用 Lambda
		 */
	}
	
}
