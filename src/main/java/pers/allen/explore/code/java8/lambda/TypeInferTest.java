package pers.allen.explore.code.java8.lambda;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Lambda表达式类型推断
 * @author lengyul
 * @date 2018年12月20日 下午9:37:20
 */
public class TypeInferTest {
	
	public static void main(String[] args) {
		//变量类型定义
		Function<Integer, Integer> f1 = x -> x * x;
		//强转
		Object f2 =  (Function<Integer, Integer>)x -> x * x;
		//通过返回类型
		Function<Integer, Integer> f3 = function();
		
		functionTwo(System.out::println); //Consumer
	//	functionTwo((x) -> x * x); //Function || SubFunction
		functionTwo((Function<Integer, Integer>)(x) -> x * x);
		
	}
	
	public static Function<Integer, Integer> function(){
		return x -> x * x;
	}
	
	public static void functionTwo(Consumer<Integer> consumer){
		
	}

	public static void functionTwo(Function<Integer, Integer> function){
		
	}
	
	public static void functionTwo(SubFunction<Integer, Integer> consumer){
		
	}
	
	
}
interface SubFunction<T,R>{
	R apply(T t);
} 
