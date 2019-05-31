package pers.allen.explore.effective.object;

import static java.util.Comparator.*;

import java.util.Comparator;

/**
 * 实现Comparable接口对类进行排序
 * @author lengyul
 * @date 2019年4月22日 下午8:05:35
 */
public class ComparableImpl implements Comparable<ComparableImpl> {

	/*
	 * 类实现了Comparable接口，其实例可以与集合实现进行协作（分类、比较等）。
	 */
	private int x;
	private int y;

	public ComparableImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// 使用Java8 Comparator接口提供的比较器构造方法
	private static final Comparator<ComparableImpl> COMPARATOR =
		comparingInt((ComparableImpl ci) -> ci.x)
		.thenComparingInt(ci -> ci.y);	
			
	
	@Override
	public int compareTo(ComparableImpl o) {
		// 不建议手动使用关系操作符来比较
		int result = Integer.compare(x, o.x);
		if (result == 0)
			result = Integer.compare(y, o.y);
		return result;
	//	return COMPARATOR.compare(this, o);
	}
	
	public static void main(String[] args) {
		ComparableImpl ci1 = new ComparableImpl(1, 2);
		ComparableImpl ci2 = new ComparableImpl(1, 2);
		
		System.out.println(ci1.compareTo(ci2));
	}
	
}
