package pers.allen.explore.pattern.singleton;

/**
 * 饿汉式
 * @author lengyul
 *  静态工厂方法：比较常用，更简单
 * 	工厂返回该类的唯一实例，具有灵活性，可通过方法引用（method reference） FactorySingleton::getInstance
 */
public class FactorySingleton {
	
	private FactorySingleton(){}
	
	private static final FactorySingleton instance = new FactorySingleton();
	
	public  static FactorySingleton getInstance(){
		
		return instance;
	}
	
	
}
