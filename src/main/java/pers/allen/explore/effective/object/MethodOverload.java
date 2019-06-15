package pers.allen.explore.effective.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 慎用重载
 * @author lengyul
 * @date 2019年6月15日 上午9:46:50
 */
public class MethodOverload {

	/*
	 * 对于多个具有相同参数数目的方法来说，应该尽量避免使用重载
	 */
	
	private static class CollectionType {

		public static String print(Collection<?> c) {
			return "collection";
		}

		public static String print(List<?> list) {
			return "list";
		}

		public static String print(Set<?> s) {
			return "set";
		}

	}
	
	public static void main(String[] args) {
		List<?> list = new ArrayList<>();
		Set<?>  set  = new HashSet<>();
		
		Collection<?> [] collections = {
				list,set
		};
		/*
		 *  调用哪个方法是在编译时做出决定的，参数的编译类型都是 Collection<?>
		 *  每次迭代运行时的类型是不同的，但这并不影响对重载方法的选择
		 */
		for (Collection<?> c : collections) {
			System.out.println(CollectionType.print(c));
		}
		
	}
	
	
}
