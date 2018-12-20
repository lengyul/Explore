package com.expolre.code.java8.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * Lambda表达式的方法引用
 * @author lengyul
 * @date 2018年12月20日 下午9:01:57
 */
public class MethodReferenceTest {
	public static void main(String[] args) {
		//方法引用
		Consumer<String> consumer = System.out::println;
		consumer.accept("Hello World");
		
		Person person = new Person("Mike");
		//静态方法的方法引用
		Consumer<Person> consumer2 = Person::print;
		consumer2.accept(person);
		
		//实例方法的方法引用
		//IntFunction function = person::setAge; //无需指定泛型
		Function<Integer, Integer> function = person::setAge;
		System.out.println("My age is "+ function.apply(20));
		
		BiFunction<Person,String,String> biFunction = Person::setName;
		System.out.println("My name is "+biFunction.apply(person,"Allen"));
		
		
		//构造函数的方法引用
		Supplier<Person> supplier = Person::new;
		System.out.println(supplier.get()); //Person对象实例
		
		Function<String,Person> function2 = Person::new;
		System.out.println(function2.apply("Emma"));
	}
}
class Person{
	
	String name;
	
	public Person(){}
	
	public Person(String name) {
		super();
		this.name = name;
	}

	public static void print(Person person){
		System.out.println("My name is "+person.name);
	}
	
	public Integer setAge(Integer age) {
		return age;
	}
	
	public String setName(Person this,String name){
		return name;
	}
	
}
