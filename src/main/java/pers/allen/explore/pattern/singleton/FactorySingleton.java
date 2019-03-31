package pers.allen.explore.pattern.singleton;

/**
 * 饿汉式
 * @author lengyul
 *  这种方式比较常用，更简单
 */
public class FactorySingleton {
	
	private FactorySingleton(){}
	
	private static final FactorySingleton instance = new FactorySingleton();
	
	/*
	 * 静态工厂方法：
	 * 	工厂返回该类的唯一实例，具有灵活性，可通过方法引用（method reference） FactorySingleton::getInstance
	 */
	public  static FactorySingleton getInstance(){
		
		return instance;
	}
	
	
}
