package pers.allen.explore.feature.java8.functions;

/*
 * 函数接口规则：
 * 一个函数接口只有一个抽象方法
 * 在 Object 类中属于公共方法的抽象方法不会被视为单一抽象方法
 * 函数接口可以有默认方法和静态方法
 */
@FunctionalInterface
public interface CalcFunction {
	
	long getValue(int x, int y);
	
	public static void main(String[] args) {
		CalcFunction calcPlus = (x,y) -> x + y;
		System.out.println(calcPlus.getValue(1, 2));
		
		CalcFunction calcMmultiply = (x,y) -> x * y;
		System.out.println(calcMmultiply.getValue(1, 2));
	}
}
