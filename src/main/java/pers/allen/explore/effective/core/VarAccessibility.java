package pers.allen.explore.effective.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 类和成员的可访问性最小化
 * @author lengyul
 * @date 2019年4月23日 下午7:11:13
 */
public class VarAccessibility {

	/*
	 * 尽可能地使每个公有类的公有域可访问性最小化，除了公有静态final域（常量），公有类不应该包含公有域
	 * 对于成员（域、方法、嵌套类和嵌套接口）有四种访问级别：
	 * 	私有的（private）：只能在该类内部访问这个成员
	 * 	包级私有的（package-private）：默认修饰符，该成员的包内部的任何类都可以访问这个成员
	 * 	受保护的（protected）：该成员的类的子类可以访问这个成员或该成员的包内部的任何类也可以访问这个成员
	 * 	公有的（public）：在任何地方都可以该成员
	 * 
	 * 在Java9中提供了 模块系统（module system）：
	 * 两种隐式访问级别：分为模块声明（module declaration）和导出声明（export declaration）来管理包
	 * module-info.java的源文件中进行配置使用
	 */
	int number = 0;
	
	/*
	 * 公有域静态常量要确保所引用对象是不可变的
	 */
	public static final int SUCCESS = 0;
	public static final String STR = "";
	
	// 引用本身不能被修改，但是所引用的对象内容可以被修改 例如：VALUES[0] = 0; 
    //	public static final int[] VALUES = {1,2,3}; 

	private static final Integer[] VALUES = {1,2,3}; 	
	// 使用公有不可变列表
	public static final List<Integer> VALUES_LIST = Collections.unmodifiableList(Arrays.asList(VALUES));

	// 使用公有方法，返回私有数组的一个拷贝
	public static Integer[] getValues() {
		return VALUES.clone();
	}
	
}
