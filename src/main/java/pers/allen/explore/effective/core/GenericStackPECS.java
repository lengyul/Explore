package pers.allen.explore.effective.core;

import java.util.Collection;

import pers.allen.explore.effective.object.ObsoleteReference.Stack;

/**
 * 利用有限制通配符提升API的灵活性
 * @author lengyul
 * @param <E>
 * @date 2019年5月11日 上午10:24:51
 */
public class GenericStackPECS<E> extends Stack<E> {
	
	/*
	 * E的某个子类型的Iterable接口，向下兼容（例：subtype）
	 */
	public void pushAll(Iterable<? extends E> src) {
		for (E e : src) {
			push(e);
		}
	}
	
	/*
	 * 不应该为E的集合（Collection<E>），E的超类的集合，向上兼容（例：Object）
	 * popAll(Collection<E> c) || popAll(Collection<Object> c)...
	 * 
	 */
	public void popAll(Collection<? super E> dst) { 
		while (size() != 0) {
			dst.add(pop());
		}
	}
	/*
	 * PECS表示 producer-extends，consumer-super
	 * 如果参数化类型表示一个生产者 T ，就使用＜? extends T＞；如果它表示 一个消费者 T ，就使用 ＜? super T＞
	 * 如果某个输入参数既是生产者，又是消费者，那么需要的是严格的类型匹配
	 */
	
}
