package pers.allen.explore.effective.core;

import java.util.Collection;

/**
 * 用enum代替int常量
 * @author lengyul
 * @date 2019年5月17日 下午8:10:43
 */
public class PriorityEnum {

	/*
	 * int枚举模式/String枚举模式
	 * 	    常量名使用前缀作为区分不具有类型安全性，可读性差
	 * 采用int枚举模式的程序是十分脆弱的，它们的int值会被编译到使用它们的客户端中，如果int枚举关联的
	 * 值发生了变化，客户端必须重新编译
	 */
	public static final int APPLE_XR = 1;
	public static final int APPLE_X = 2;
	public static final int APPLE_MS = 3;
	
	public static final int HUAWEI_HONOR = 1;
	public static final int HUAWEI_P = 2;
	public static final int HUAWEI_MATE = 3;
	
	/*
	 * 枚举类型：每当需要一组固定常量，并且在编译时就知道其成员的时候，就应该使用枚举
	 * 单例的泛型化：不允许创建枚举类型的实例，也不能对它进行扩展
	 * 优点：枚举的可读性更好，安全功能也更加强大
	 */
	public enum APPLE {
		XR(1),
		X(2),
		MS(3);
		
		private int model;
		
		/**
		 * 自定义构造
		 * @param model
		 */
		APPLE(int model) {
			this.model = model;
		}
		
		public int getModel() {
			return model;
		}

	}
	
	public enum HUAWEI {
		HONOR,
		P,
		MATE;
		
		public static HUAWEI type(int model) {
			switch (model) {
			case 1:
				return HONOR;
			case 2:
				return P;
			default:
				return MATE;
			}
		}	
	}
	
	/**
	 * 用接口模拟可扩展的枚举
	 * @author lengyul
	 * @date 2019年5月18日 下午5:40:40
	 */
	public interface Operation {
		double apply(double x, double y);
	}
	
	public enum BasicOperation implements Operation {
		PLUS("+") {
			public double apply(double x, double y) {return x + y;}
		},
		MINUS("-") {
			public double apply(double x, double y) {return x - y;}
		},
		TIMES("*") {
			public double apply(double x, double y) {return x * y;}
		},
		DIVIDE("/") {
			public double apply(double x, double y) {return x / y;}
		};
		
		private final String symbol;
		BasicOperation(String symbol) {
			this.symbol = symbol;
		}
		@Override
		public String toString() {
			return symbol;
		}
	}
	
	public enum ExtendedOperation implements Operation {
		EXP("^") {
			public double apply(double x, double y) {return Math.pow(x, y);}
		},
		REMAINDER("%") {
			public double apply(double x, double y) {return x % y;}
		};
		
		private final String symbol;
		ExtendedOperation(String symbol) {
			this.symbol = symbol;
		}
		
		@Override
		public String toString() {
			return symbol;
		}
	}
	
	@SuppressWarnings("unused")
	private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
		for (Operation op : opEnumType.getEnumConstants()) {
			System.out.println(op.apply(x, y));
		}
	}
	
	@SuppressWarnings("unused")
	private static void test(Collection<? extends Operation> osSet, double x, double y) {
		for (Operation op : osSet) {
			System.out.println(op.apply(x, y));
		}
	}
	
	
}
