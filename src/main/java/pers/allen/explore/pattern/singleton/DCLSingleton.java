package pers.allen.explore.pattern.singleton;

/**
 * 双重检查模式（double-check idiom）
 * @author lengyul
 * @date 2019年6月30日 下午12:15:22
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
