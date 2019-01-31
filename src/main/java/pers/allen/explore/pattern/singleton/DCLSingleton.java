package pers.allen.explore.pattern.singleton;

/**
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 * @author lengyul
 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 */
public class DCLSingleton {
	
	private DCLSingleton(){}
	
	private static DCLSingleton instance = null;
	
	public static DCLSingleton getInstance(){
		if (instance == null) {
			synchronized (DCLSingleton.class) {
				if (instance == null) {
					instance = new DCLSingleton();
				}
			}
		}
		return instance;
	}
	
}
