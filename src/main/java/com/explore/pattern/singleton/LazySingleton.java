package com.explore.pattern.singleton;

/**
 * 懒汉式
 * @author lengyul
 *  这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式
 *  synchronized 这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
 */
public class LazySingleton {
	
	private LazySingleton(){}
	
	private static LazySingleton instance;
				//懒汉式+synchronized,线程安全(每次都会sync效率低)
	public static synchronized LazySingleton getInstance(){
		if (instance==null) {
			instance =new LazySingleton();
		}
		return instance;
	}
	
}
