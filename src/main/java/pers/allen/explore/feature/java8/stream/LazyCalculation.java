package pers.allen.explore.feature.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 通常是 lazy的，直到调用终止操作时才会开始计算
 * @author lengyul
 * @date 2019年5月29日 上午11:25:14
 * 惰性计算：意味着一个函数可以现在或以后计算，或者可以完全跳过计算
 * 
 * 传递给操作的 lambda 表达式和闭包应该是纯的（不可变），从函数管道的开始到结束，闭包所依赖的状态绝不应被多个线程修改
 * 函数纯度的规则：
 * 	函数不会更改任何元素
 *  函数不依赖于任何可能改变的元素
 */
public class LazyCalculation {
	
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		
		int[] factor = new int[] { 2 };
		Stream<Integer> stream = numbers.stream().map(e -> e * factor[0]);
		/*
		 * map 为中间操作，forEach 为终结操作
		 * map 的闭包依赖的变量可能发生变化，由于惰性计算，作为参数传递给 map 的闭包只在调用 forEach 后才会计算
		 */
		factor[0] = 0;
		stream.forEach(System.out::println);
	}
}
