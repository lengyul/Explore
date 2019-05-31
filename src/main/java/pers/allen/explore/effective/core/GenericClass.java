package pers.allen.explore.effective.core;

/**
 * 泛型类和接口统称为泛型（generic type）
 * @author lengyul
 * @date 2019年4月27日 下午9:56:48
 * @param <T>
 */
public class GenericClass<T> {
	
	/*
	 * 声明具有一个或多个类型参数（type parameter）的类或者接口，就是泛型类或者接口（例如：List<E>）
	 * E（element）：用于元素类型
	 * T （type）：用于Java类
	 * List<String> 参数化的类型，String为实际类型参数
	 * List<E> 泛型，E为形式类型参数
	 * List<?> 无限制通配符类型
	 * List    原生态类型
	 * <E extends Number> 有限制类型参数
	 * <T extends Comparable<T>> 递归类型限制
	 * List<? extends Number> 有限制通配符类型
	 * static <E> List<E> asList(E[] a) 泛型方法
	 * String.class 类型令牌
	 * 
	 */

	private int code;

	private T data;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
