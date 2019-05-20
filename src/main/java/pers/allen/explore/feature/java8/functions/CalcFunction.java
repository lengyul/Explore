package pers.allen.explore.feature.java8.functions;

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
