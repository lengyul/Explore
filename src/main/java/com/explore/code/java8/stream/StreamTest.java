package com.explore.code.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java8 Stream API操作
 * @author lengyul
 * @date 2018年12月11日 下午3:57:01
 * 常见中间操作：
 * map：通过一个 Function 把一个元素类型为 T 的流转换成元素类型为 R 的流。
 * flatMap：通过一个 Function 把一个元素类型为 T 的流中的每个元素转换成一个元素类型为 R 的流，再把这些转换之后的流合并。
 * filter：过滤流中的元素，只保留满足由 Predicate 所指定的条件的元素。
 * distinct：使用 equals 方法来删除流中的重复元素。
 * limit：截断流使其最多只包含指定数量的元素。
 * skip：返回一个新的流，并跳过原始流中的前 N 个元素。
 * sorted：对流进行排序。
 * peek：返回的流与原始流相同。当原始流中的元素被消费时，会首先调用 peek 方法中指定的 Consumer 实现对元素进行处理。
 * dropWhile：从原始流起始位置开始删除满足指定 Predicate 的元素，直到遇到第一个不满足 Predicate 的元素。
 * takeWhile：从原始流起始位置开始保留满足指定 Predicate 的元素，直到遇到第一个不满足 Predicate 的元素。
 * ---------------------------------------------------------------------------------------------
 * 常见终止操作：
 * forEach：对流中的每个元素执行由Consumer给定的实现。
 * reduce：把一个流约简成单个结果。
 * Max 和 min是两种特殊的约简操作，分别求得流中元素的最大值和最小值。
 * 操作 allMatch、anyMatch 和 nonMatch 分别用来检查是否流中的全部元素、任意元素或没有元素满足给定的条件。
 * 操作 findFirst 和 findAny分别查找流中的第一个或任意一个元素。
 * 操作 collect 表示的是另外一类的约简操作，与 reduce 不同在于，collect 会把结果收集到可变的容器中，如 List 或 Set。
 */
public class StreamTest {
	
	public static void main(String[] args) {
		//----------------Stream的创建方式-------------
		//Stream
		Stream.of(1,2,3,4,5);
		Stream.generate(() -> 1);
		//Collection
		List<String> list = new ArrayList<>();
		list.stream();
		list.parallelStream();
		//Array
		Arrays.stream(new int[]{1,2,3,4,5});
		//Number
		IntStream.of(1,2,3,4,5);
		//Random
		IntStream is = new Random().ints().limit(10);
		
		//----------------Stream的中间操作-------------
		list.add("A");
		list.add("B");
		list.add("C");
		list.stream().map(x -> x.length()).forEach(System.out::println);
	list.stream().filter(x -> x.equals("B")).map(x -> x.toLowerCase()).forEach(System.out::println);
		//peek用于debug，是个中间操作
		list.stream().peek(System.out::println);
		//limit用于无限流
		new Random().ints().filter(x -> x < 100).limit(10).forEach(System.out::print);
		
		System.out.println();
		//----------------Stream的终止操作-------------
		String str = "hello world!";
		//使用并行流
		str.chars().parallel().forEach(i -> System.out.print((char)(i)));
		System.out.println();
		str.chars().parallel().forEachOrdered(i -> System.out.print((char)(i)));
		//收集器
		Stream.of(str.split(" ")).collect(Collectors.toList()).forEach(System.out::println);;
		Optional<String> op = Stream.of(str.split(" ")).reduce((x,y) -> x +"0"+ y);
		op.ifPresent(System.out::println);
		//max || min 
		op = Stream.of(str.split(" ")).max((x,y) -> x.length() - y.length());
		System.out.println(op.get());
		//findFirst
		op = list.stream().findFirst();
		System.out.println(op.get());
		
	}
	
	
}
