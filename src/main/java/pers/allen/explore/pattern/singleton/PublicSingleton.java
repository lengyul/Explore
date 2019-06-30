package pers.allen.explore.pattern.singleton;

/**
 * 公有域单例（优先使用）
 * @author lengyul
 * 
 */
public class PublicSingleton {
	
	private PublicSingleton(){}
	
	// 正常的初始化
	public static final PublicSingleton INSTANCE = new PublicSingleton();
	
}
