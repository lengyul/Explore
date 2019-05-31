package pers.allen.explore.effective.object;

/**
 * 重叠构造器（多个构造器参数时考虑使用）
 * @author lengyul
 * @date 2019年3月30日 下午12:49:56
 */
public class TelescopingConstructor {
	/*
	 * 当想创建实例，就利用参数列表最短的构造器
	 * new TelescopingConstructor(0);
	 * new TelescopingConstructor(1,20,175,60,"Allen");
	 * 但当有许多参数的时候，客户端代码会很难编写，并且难以阅读，如果不小心颠倒了参数顺序，运行时可能数据会出现问题
	 * 
	 */
	private final int id;
	private final int age;
	private final int heigh;
	private final int weight;
	private final String name;
	
	public TelescopingConstructor(int id) {
		this(id, 0 );
	}
	
	public TelescopingConstructor(int id, int age) {
		this(id, age, 0);
	}
	
	public TelescopingConstructor(int id, int age, int heigh) {
		this(id, age, heigh, 0);
	}
	
	public TelescopingConstructor(int id, int age, int heigh, int weight) {
		this(id, age, heigh, weight, null);
	}
	
	public TelescopingConstructor(int id, int age, int heigh, int weight, String name) {
		this.id = id;
		this.age = age;
		this.heigh = heigh;
		this.weight = weight;
		this.name = name;
	}
	
	
}
