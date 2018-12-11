package com.expolre.code.stream;

import java.util.Arrays;

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
 * 常见终结操作：
 * forEach：对流中的每个元素执行由Consumer给定的实现。
 * reduce：把一个流约简成单个结果。
 * Max 和 min是两种特殊的约简操作，分别求得流中元素的最大值和最小值。
 * 操作 allMatch、anyMatch 和 nonMatch 分别用来检查是否流中的全部元素、任意元素或没有元素满足给定的条件。
 * 操作 findFirst 和 findAny分别查找流中的第一个或任意一个元素。
 * 操作 collect 表示的是另外一类的约简操作，与 reduce 不同在于，collect 会把结果收集到可变的容器中，如 List 或 Set。
 */
public class StreamTest {
	
	public static void main(String[] args) {
		
		Arrays.stream(new int[]{1,2,3,4,5});
	}
	
	
}
