package pers.allen.explore.effective.core;

import org.junit.Test;

/**
 * Stream API 
 * @author lengyul
 * @date 2019年6月5日 下午8:32:12
 */
public class Stream {

	/*
	 * 	在Java 8 中增加了 Stream API，简化了串行或并行的大批量操作
	 * Stream（流）代表数据元素有限或无限的顺序，Stream pipeline（流管道）则代表这些元素的一个多级计算
	 * 
	 * Stream 中的元素可能来自任何位置，常见的来源包括集合、数组、文件、伪随机数生成器以及其他的 Stream
	 * @see java.util.stream.Stream[static of(T... values)]
	 * @see Collection[default stream()] 			集合
	 * @see Arrays[static stream(T[] array)] 		数组
	 * @see JarFile[stream()] 						文件
	 * @see Random[ints(long streamSize)] 			随机
	 * 
	 * 
	 * Stream 中的数据元素可以是对象引用、或者基本数据类型，它支持三种基本类型：int、long 和 double
	 * @see IntStream LongStream DoubleStream
	 */
	
	/*
	 * 
	 * 	一个 Stream pipeline 中包含一个源 Stream，接着是 0个或者多个中间操作（intermediate operation）
	 * 和一个终止操作（terminal operation）
	 * 中间操作：对 Stream 中的元素进行转换、改变、过滤等
	 * 终止操作：会在最后一个中间操作产生的 Stream 上执行一个最终的计算，例如：返回集合、某个元素或者打印出所有元素等
	 * @see StreamTest
	 * 
	 * Stream pipeline 通常是 lazy 的：直到调用终止操作时才会开始计算（没有终止操作的 Stream 不会执行中间操作）
	 * @see LazyCalculation
	 * 
	 * Stream API 是流式（fluent）的：所有包含 pipeline 的调用可以链接成一个表达式
	 * @see CollectionStream 	list.stream().filter().map()...
	 */
	
	
	/*
	 * 避免利用 Stream 来处理 char 值
	 */
	@Test
	public void test() {
		// chars() 返回的 IntStream 中的元素，并不是 char 值，而是 int 值
		"Hello world".chars().forEach(System.out::print);
		 System.out.println();
		"Hello world".chars().forEach(x -> System.out.print((char) x));
	}
	
	/*
	 * 使用 Stream 的一些优势
	 * 可以使用并行流对元素进行操作 [parallelStream() || stream().parallel()]
	 * 统一转换、过滤元素的序列 [map()、reduce()、filter()...]
	 * 计算元素、合并元素的顺序 [flatMap()、max()、min()..]
	 * 对元素进行去重、排序、收集、分组 [distinct()、sorted()、collect()、Collectors.groupingBy()...]
	 * @see StreamTest
	 * -----------------------------------------------------------
	 * 
	 * 使用 Stream 不能完成的一些工作
	 * Lambda 中不能修改任何 local 变量
	 * forEach 中无法获取当前执行的位置（i），不能使用 return ?、break、continue 或者抛出受检异常
	 * @see forEachTest()
	 * 
	 * 对于 Stream forEach 和 迭代，具体选择哪一种方法，并没有硬性、速成的规则，如果不确定，
	 * 那么就两种都试试，看一看哪种更好用
	 */
	
	@SuppressWarnings("unused")
	@Test
	public void forEachTest() {
		String name = "Allen"; // 如果在 lambda 中引用默认加上 final
		"Hello world".chars().forEach((x) -> {
		// Local variable name defined in an enclosing scope must be final or effectively final
		// name = "Emma"; 
			System.out.print(x);
		// cannot be used
		// break; continue;
		});
	}
	
}
