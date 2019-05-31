package pers.allen.explore.effective.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 复合优先于继承
 * @author lengyul
 * @date 2019年4月24日 下午7:14:17
 */
public class CompositionImpl<E> {

	/*
	 * 复合：既不扩展现有的类，而是在类中添加一个私有的域，它引用现有类的一个实例。
	 * 
	 * 继承的功能非常强大，但是它违背了封装原则（子类依赖于其超类的功能实现细节
	 * ，如果超类的实现发生改变，那么子类可能会遭到破坏，即使它的代码没有任何改变） 
	 * 一般来说：只有当子类真正是超类的子类型时（is-a），才适合用继承
	 *
	 */
	
	/**
	 * 使用组合实现（应该提供一个转发类，它实现了接口，并且包含了所有的转发方法，使该类继承它）
	 * @author lengyul
	 * @date 2019年4月24日 下午7:44:05
	 * @param <E>
	 */
	private static class CompositionSet<E> {
		/*
		 * 类似于包装类，可以包装任何Set实现，这也正是Decorator（修饰者）模式，通过包装对象来扩展功能
		 */
		private int addCount = 0;
		
		private final Set<E> s;
		
		public CompositionSet(Set<E> s) {
			this.s = s;
		}
		
		public boolean add(E e) {
			addCount++;
			return s.add(e);
		}
		
		public boolean addAll(Collection<? extends E> c) {
			addCount += c.size();
			return s.addAll(c);
		}
		
		public int getAddCount() {
			return addCount;
		}
		
	}
	
	/**
	 * 使用继承实现
	 * @author lengyul
	 * @date 2019年4月24日 下午7:38:37
	 * @param <E>
	 */
	private static class SubClass<E> extends HashSet<E> {

		private int addCount = 0; // 用于记录调用Set.add方法次数

		public int getAddCount() {
			return addCount;
		}
		
		@Override
		public boolean add(E e) {
			addCount++;
			return super.add(e);
		}

		/*
		 *  addAll方法存在自用性：超类里可覆盖的方法调用了别的可覆盖方法
		 *  addAll方法是基于它的add方法来实现的，
		 *  首先调用addAll方法+3，然后又依次调用了SubClass覆盖的add方法三次，所以结果为6
		 *  虽然可以去掉addAll方法或者对其方法进行修改就可以使计数正常工作，但是这种方法可能非常耗时，实现起来比较困难
		 *  如果HashSet类的实现方法发生了变化或者添加了新方法，这样得到的类是非常脆弱的
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			addCount += c.size();
			return super.addAll(c);
		}
		
		/*@Override
		public boolean addAll(Collection<? extends E> c) {
			c.stream().forEach((s) -> {
				add(s);
			});
			return true;
		}*/
		
	}
	
	public static void main(String[] args) {
		
		List<Integer> arr = Arrays.asList(1,2,3);
		
		// extends impl
		SubClass<Integer> list = new SubClass<>();
		list.addAll(arr);
		System.out.println(list.getAddCount()); 
		
		// composition impl
		CompositionSet cs = new CompositionSet<>(list);
		cs.addAll(arr);
		System.out.println(cs.getAddCount()); 
	}
	
	
}
