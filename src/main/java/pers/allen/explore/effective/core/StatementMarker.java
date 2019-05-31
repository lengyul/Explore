package pers.allen.explore.effective.core;

import java.io.Serializable;

import org.junit.Test;

/**
 * 标记（注解/接口）
 * @author lengyul
 * @date 2019年5月18日 下午6:24:27
 */
public class StatementMarker implements Serializable, Cloneable {

	/*
	 * 标记接口（marker interface）是不包含方法声明的接口，它只是指明（或 标明）一个类实现了具有某种属性的接口
	 * 标记接口的存在，允许在编译时就能捕捉到在使用标记注解的情况下要到运行时才能捕捉的错误
	 * 
	 * 实现 Serializable接口，类表明它的实例可以被写到 ObjectOutputStream中或者（被序列化）
	 */
	
	/*
	 * 标记注解（marker annotation）：用于标记声明
	 * 线程安全类、不安全类、覆盖
	 * @ThreadSafe，@NotThreadSafe，Override
	 */
	@Test // 测试方法
	public void test() {}
	
	/*
	 * 如果想要定义一个任何新方法都不会与之关联的类型，标记接口就是最好的选择（因为只有类和接口可以用来实现或者扩展接口）；
	 * 如果想要标记程序元素而非类和接口，或者标记要适合于已经广泛使用了注解类型的框架，那么标记注解就是正确的选择
	 */
	
}
