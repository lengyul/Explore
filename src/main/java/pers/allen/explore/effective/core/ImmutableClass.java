package pers.allen.explore.effective.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 不可变类：其实例不能被修改的类（String、基本类型的包装类等）
 * @author lengyul
 * @date 2019年4月24日 上午7:34:16
 */
public class ImmutableClass {

	/*
	 * 不可变类比可变类更加易于设计、实现和使用，不容易出错，且更加安全
	 * 不可变类唯一的缺点是：对于每个不同的值都需要去创建一个单独对象
	 * 设计不可变类需要遵循下面五条规则：
	 * 	1.不要提供修改对象状态的方法（设值方法，如：set）
	 * 	2.保证类不会被扩展（final修饰类），防止子类恶意破坏类的不可变行为，或使用静态工厂+私有构造器来实现
	 * 	3.声明的所有域都是final的，除非有理由让某个域变成非final的（如：缓存hash code）
	 * 	4.声明的所有域都为私有的，防止客户端获取该域引用的可变对象，直接修改这些对象
	 * 	5.确保对于任何可变组件的互斥访问（类有指向可变对象的域，确保客户端无法获取指向这些对象的引用）
	 * 
	 * 总之，除非有很好的理由要让类成为可变的类，否则它就应该是不可变的；如果类不能做成不可变的，仍然应该尽可能
	 * 的限制它的可变性。
	 */
	
	private final int x;
	private final int y;
	
	private int hash; // 缓存hashcode，内部计算可能发生改变但是不会对外提供设值方法

	// final域指向的是可变对象，不能返回该对象引用（或者使用深拷贝）
	// private final List<Integer> list = new ArrayList<>();
	   private final List<Integer> list = Collections.unmodifiableList(Arrays.asList(1,2));
	
	/*
	 * 不可变对象是线程安全的，可以被自由地共享
	 */
	public static final ImmutableClass ZERO_ONE = new ImmutableClass(0, 1);
	public static final ImmutableClass ONE_TWO = new ImmutableClass(1, 2);
	
	// 使用 静态工厂方法+私有构造器 使类变成final
	private ImmutableClass(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public static ImmutableClass valueOf(int x, int y) {
		return new ImmutableClass(x, y);
	}
	 
	public ImmutableClass plus(ImmutableClass u) {
		return new ImmutableClass(x + u.x, y + u.y);
	}
	
	public ImmutableClass minus(ImmutableClass u) {
		return new ImmutableClass(x - u.x, y - u.y);
	}
	
	//....
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public List<Integer> getList() {
		return list;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ImmutableClass))
			return false;
		ImmutableClass u = (ImmutableClass)obj;
		return Integer.compare(u.x, x) == 0
			&& Integer.compare(u.y, y) == 0;
	}
	
	@Override
	public int hashCode() {
		int result = hash;
		if (result == 0) {
			result = Objects.hash(x,y);
			hash = result;			
		}
		return result;
	}
	
}
