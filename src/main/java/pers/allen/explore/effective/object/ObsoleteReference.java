package pers.allen.explore.effective.object;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 消除对象的过期引用
 * @author lengyul
 * @date 2019年5月11日 上午9:42:02
 */
public class ObsoleteReference {

	
	public static class Stack<E> {
		
		private E[] elements;
		private int size;
		private static final int DEFAULT_CAPACITY = 16;
		
		@SuppressWarnings("unchecked")
		public Stack() {
			elements = (E[]) new Object[DEFAULT_CAPACITY];
		}
		
		public void push(E e) {
			ensureCapacity();
			elements[size++] = e;
		}
		
		/*public E pop() {
			if (size == 0) {
				throw new EmptyStackException();
			}
			return elements[--size]; // 该对象不会被回收，栈内部维护着对这些对象的过期引用
		}*/
		public E pop() {
			if (size == 0) {
				throw new EmptyStackException();
			}
			E e = elements[--size];
			elements[size] = null; // 消除过期引用
			return e;
		}
		
		public int size() {
			return size;
		}
		
		private void ensureCapacity() {
			if (elements.length == size) 
				elements = Arrays.copyOf(elements, 2 * size + 1);
		}
		
	}

	
}
