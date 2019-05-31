package pers.allen.explore.effective.object;

/**
 * 工具类（只包含静态方法和静态域的类）
 * @author lengyul
 * @date 2019年3月31日 下午2:49:29
 */
public class UtilityClass {
	
	String s = "";
	static String str = "";
	/*
	 * 工具类不希望被实例化，因为实例化对它没有任何意义（编写会默认提供一个共有的，无参爱的缺省构造器）default constructor
	 * AssertionError不是必需的，只是避免在类的内部调用构造器
	 * 它使得一个类不能被子类化，子类没有可访问超类构造器可调用
	 */
	private UtilityClass() {
		throw new AssertionError();
	}
	
	private class A {
		
		public void print() {
			System.out.println(s);
			System.out.println(str);
		}
	}
	
	private static class B {
		public void print() {
			System.out.println(str);
		}
	}
	
}
