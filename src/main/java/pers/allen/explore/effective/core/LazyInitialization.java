package pers.allen.explore.effective.core;

/**
 * 慎用延迟初始化
 * @author lengyul
 * @date 2019年6月30日 下午12:24:10
 */

class FieldType { } // 定义初始化域

public class LazyInitialization {

	
	/*
	 * 延迟初始化（lazy initialization） 是指延迟到需要域的值时才将它初始化的行为；
	 * 如果永远不需要这个值，这个域就永远不会被初始化；这种方法既适用于静态域，也适用于实例域
	 */
	
	/**
	 * 实例域 正常初始化
	 */
	
		/*
		 * 在大多数情况下，正常的初始化要优先于延迟初始化
		 */
		private class Normal {
			
			// 改变为静态域只需要（域和方法） 加上 static 关键字
			private final FieldType field = new FieldType();
			
			public FieldType getField() {
				return field;
			}
			
		}
	
	/**
	 * 实例域 延迟初始化
	 */
	
		/*
		 * 使用同步方法的延迟初始化
		 * 它是最简单、最清楚的实现方法，但是对于并发时性能并不高，不是更好的选择
		 */
		private class Sync {
			
			private FieldType field;
		
			public synchronized FieldType getField() {
				if(field == null)
				   field = new FieldType();
				return field;
			}
			
		}

		/*
		 * 如果处于性能的考虑而需要对实例域进行延迟初始化，就使用双重检查模式（double-check idiom）
		 * @see DCLSingleton
		 */
		 private class DoubleCheck {
			 
			 private volatile FieldType field;
			 
			 private FieldType getField() {
				 FieldType result = field; // 使用局部变量，提升性能、标准、更加优雅
				 if (result == null) { // 第一次检查（未持有锁）
					synchronized (this) {
						if (field == null) // 第二次检查（持有锁）
							field = result = new FieldType();
					}
				}
				 return result;
			 }
			 
		 }
		 
		 /*
		  * tips:
		  * 	 虽然也可以对静态域应用 双重检查模式 ，但是没有理由这么做，因为 
		  * lazy initialization holder class 模式 是更好的选择
		  * @see StaticFieldType
		  */
		 
		 /*
		  * 单重检查模式（对于可以接受重复初始化的实例域）
		  */
		 private class SingleCheck {

			 private volatile FieldType field;

			 private FieldType getField() {
				 FieldType result = field;
				 if (result == null)
					 field = result = new FieldType();
					 return result;
			 }
			 
		 }
	
	
	
	/**
	 * 静态域 延迟初始化
	 */
	private static class StaticFieldType {
		
		// lazy initialization holder class 模式，保证了类要被用到的时候才会初始化
		private static class StaticFieldHolder {
			static final FieldType field = new FieldType();
		}
		
		public static FieldType getField() {
			return StaticFieldHolder.field;
		}
	}
	
	
	/*
	 * 	   总之：大多数的域应该正常地进行初始化，而不是延迟初始化；如果为了性能或其它目的
	 * 而必须延迟初始化一个域，就可以使用相应的延迟化方法
	 * 
	 * 对于实例域，就使用双重检查模式（double-check idiom）
	 * 对于静态域，则使用 lazy initialization holder class idiom
	 * 对于可以接受重复实例化的实例域，也可以考虑使用单重检查模式（single-check idiom）
	 */
		 
}
