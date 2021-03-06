package pers.allen.explore.pattern.singleton;

/** 
 * 静态内部类（lazy-on-demand holder class idiom）
 * @author lengyul
 * 私有内部类对静态域使用延迟初始化，
 * 应使用这种方式而不是双检锁方式，这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用
 */
public class ICSingleton {
  
	private static class SingletonHolder{
		private static final ICSingleton INSTANCE = new ICSingleton();  
	}
	
	private ICSingleton(){}
	
	/*
	 * 	  当 getInstance() 被调用时，它第一次读取 SingletonHolder.INSTANCE，
	 * 导致 SingletonHolder 类得到初始化
	 */
	public static final ICSingleton getInstance(){
	
		return SingletonHolder.INSTANCE;
	}
	
	
}
