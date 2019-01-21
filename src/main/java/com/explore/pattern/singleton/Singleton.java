package com.explore.pattern.singleton;

/**
 * 饿汉式
 * @author lengyul
 *  这种方式比较常用，但容易产生垃圾对象。
 */
public class Singleton {
	
	private Singleton(){}
	
	private static Singleton instance =new Singleton();
	
	public  static Singleton getInstance(){
		
		return instance;
	}
	
	
}
