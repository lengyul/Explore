package pers.allen.explore.feature.java8.lambda;

import java.util.function.Function;

/**
 * Lambda表达式 级联表达式和柯里化
 * @author lengyul
 * @date 2018年12月20日 下午9:59:11
 * 柯里化：是把有多个输入参数的求值过程，转换成多个只包含一个参数的函数的求值过程。
 */
public class CurryingTest {
	public static void main(String[] args) {
		
		Function<Integer,Function<Integer,Integer>> function = x -> y -> x * y;
		System.out.println(function.apply(2).apply(3));
		
	}
}
