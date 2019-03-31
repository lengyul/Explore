package pers.allen.explore.pattern.singleton;

/**
 * 公有域单例（优先使用）
 * @author lengyul
 *  
 */
public class PublicSingleton {
	
	private PublicSingleton(){}
	
	public static final PublicSingleton INSTANCE = new PublicSingleton();
	
}
