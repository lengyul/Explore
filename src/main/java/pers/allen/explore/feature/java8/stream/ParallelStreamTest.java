package pers.allen.explore.feature.java8.stream;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 并行流（使用ForkJoinPool中的commonPool）
 * @author lengyul
 * @date 2019年5月29日 上午11:08:46
 * 
 * forEach 操作是终止操作，它是显式迭代，因而不适合并行
 */
public class ParallelStreamTest {

	
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
		
		LocalDateTime start = LocalDateTime.now();
		
		// 使用并行流在 8 核心处理器所用时间约为 1 秒，因为在默认情况下，并行流使用了与系统上的核心数一样多的线程
		numbers.stream().parallel()
			.map(ParallelStreamTest::simulateTimeComputation)
			.forEachOrdered(System.out::println);
		
		LocalDateTime end = LocalDateTime.now();
		System.out.println("Seconds：" + Duration.between(start, end).getSeconds());
	}
	
	/*
	 * 延时操作
	 */
	private static int simulateTimeComputation(int number) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return number * 2;
	}
	
}
