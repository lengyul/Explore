package pers.allen.explore.pattern.singleton;

/**
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 * @author lengyul
 * 采用双锁机制，安全且在多线程情况下能保持高性能
 */
public class DCLSingleton {
	
	private DCLSingleton(){}
	
	/*
	 * 对象初始化
	 * 1.分配内存
	 * 2.对象赋值
	 * 3.分配内存地址
	 */
	private volatile static DCLSingleton instance = null; // 使用volatile禁止指令重排，并不是内存可见性
	
	public static DCLSingleton getInstance() {
		if (instance == null) { // 如果出现指令重排返回instance可能为空
			synchronized (DCLSingleton.class) {
				if (instance == null) {
					instance = new DCLSingleton();
				}
			}
		}
		return instance;
	}
	
}
