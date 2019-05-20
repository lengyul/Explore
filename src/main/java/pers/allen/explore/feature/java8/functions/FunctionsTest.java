package pers.allen.explore.feature.java8.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.junit.Test;

/**
 * Java8 函数式编程
 * @author lengyul
 * @date 2018年12月10日 上午11:30:05
 * Java 标准库提供了几种特殊类型的函数：
 * Function<T, R> 表示接受一个参数的函数，输入类型为 T，输出类型为 R
 * BiFunction<T, U, R>，T 和 U 分别是两个参数的类型，R 是输出类型
 * Consumer<T>：接受一个输入，没有输出。抽象方法为 void accept(T t)
 * Supplier<T>：没有输入，一个输出。抽象方法为 T get()
 * Predicate<T>：接受一个输入，输出为 boolean 类型。抽象方法为 boolean test(T t)
 * UnaryOperator<T>：接受一个输入，输出的类型与输入相同，相当于 Function<T, T>
 * BinaryOperator<T>：接受两个类型相同的输入，输出的类型与输入相同，相当于 BiFunction<T,T,T>
 * BiPredicate<T, U>：接受两个输入，输出为 boolean 类型。抽象方法为 boolean test(T t, U u)
 */
public class FunctionsTest {
	
	public static void main(String[] args) {
		String name = "Alex";
		new Thread(() -> System.out.println("Hello, " + name)).start();
		//name = "1";
	}

	//Function<T, R>
	public Integer function(Integer number,Function<Integer,Integer> f){	
		return f.apply(number);
	}
	@Test
	public void testFunction(){
		int result = function(100,(x) -> x + 1);
		System.out.println(result);
	}
	//------------------------------------------------------------------
	
	
	//BiFunction<T, U, R>
	public Integer biFunction(Integer n1,Integer n2,BiFunction<Integer, Integer, Integer> bif){
		return bif.apply(n1, n2);
	}
	@Test
	public void testBiFunction(){
		int result = biFunction(1,1,(x,y) -> (x + y));
		System.out.println(result);
	}
	//------------------------------------------------------------------
	
	
	//Consumer<T>
	public void consumerFunction(String str,Consumer<String> conf){
		conf.accept(str);
	}
	@Test
	public void testConsumerFunction(){
		consumerFunction("functional programming",x -> System.out.println(x.toUpperCase()));
		consumerFunction("函数式编程",x -> System.out.println(x));
	}
	//------------------------------------------------------------------
	
	
	//Supplier<T>
	public Integer supplierFunction(Supplier<Integer> supf){
		return supf.get();
	}
	public List<Integer> supplierFunction(int number,Supplier<Integer> supf){
		List<Integer> lists = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			lists.add(supf.get());
		}
		return lists;
	}
	@Test
	public void testSupplierFunction(){
		int result = supplierFunction(() -> (int)(Math.random() * 10));
		System.out.println(result);
		List<Integer> lists = supplierFunction(5, () -> (int)(Math.random() * 10));
		lists.stream().forEach(System.out::println);
				
	}
	//------------------------------------------------------------------
	
	
	//Predicate<T>
	public boolean predicateFunction(Integer number,Predicate<Integer> pref){
		return pref.test(number);
	}
	public List<Integer> predicateFunction(List<Integer> number,Predicate<Integer> pref){
		Objects.requireNonNull(number);
		List<Integer> lists = new ArrayList<>();
		for (Integer integer : number) {
			if (pref.test(integer)) {
				lists.add(integer);
			}
		}
		return lists;
	}
	@Test
	public void testPredicateFunction(){
		boolean result = predicateFunction(1,(x) -> (x == 0));
		System.out.println(result);
		//2
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		predicateFunction(numbers, (x) -> (x % 2 == 0)).stream().forEach(System.out::println);
	}
	//------------------------------------------------------------------
	
	
	//UnaryOperator<T>
	public String unaryOperatorFunction(String str,UnaryOperator<String> unaryf){
		return unaryf.apply(str);
	}
	@Test
	public void testUnaryOperatorFunction(){
		String result = unaryOperatorFunction("abc",(x) -> x.toUpperCase());
		System.out.println(result);
	}
	//------------------------------------------------------------------
	
	
	//BinaryOperator<T>
	public Integer binaryOperatorFunction(Integer n1,Integer n2,BinaryOperator<Integer> binaryf){
		return binaryf.apply(n1, n2);
	}
	@Test
	public void testBinaryOperatorFunction(){
		int result = binaryOperatorFunction(1, 2,(x,y) -> (x+y));
		System.out.println(result);
	}
	//------------------------------------------------------------------
	
	
	//BiPredicate<T, U>
	public boolean biPredicateFunction(Integer n1,Integer n2,BiPredicate<Integer,Integer> bipref){
		return bipref.test(n1, n2);
	}
	@Test
	public void testBiPredicateFunction(){
		boolean result = biPredicateFunction(1, 2,(x,y) -> (x == y));
		System.out.println(result);
	}
	
}
