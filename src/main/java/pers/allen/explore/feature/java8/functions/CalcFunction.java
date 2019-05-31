package pers.allen.explore.feature.java8.functions;

/*
 * 自定义函数接口
 * 1.使用 @FunctionalInterface 注释该接口
 * 2.确保该接口只有一个抽象方法
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
