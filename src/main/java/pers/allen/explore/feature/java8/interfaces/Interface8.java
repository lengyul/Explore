package pers.allen.explore.feature.java8.interfaces;

/**
 * Java8 接口（默认方法和静态方法）
 * @author lengyul
 * @date 2019年4月12日 上午11:00:21
 * jdk7： 只能声明全局常量和抽象方法
 * jdk8： 添加默认方法和静态方法
 */
public interface Interface8 {
	
	default void init() {
		System.out.println("default method");
	}
	
	static void print() {
		System.out.println("static method");
	}
	
	class InterfaceImpl implements Interface8 {
		InterfaceImpl() { init();}
	}
	
	public static void main(String[] args) {
		new InterfaceImpl();
		Interface8.print();
	}
}
