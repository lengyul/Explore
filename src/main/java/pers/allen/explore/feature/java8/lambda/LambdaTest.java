package pers.allen.explore.feature.java8.lambda;

/**
 * Java8 Lambda表达式
 * @author lengyul
 * @date 2018年12月20日 下午8:09:54
 */
public class LambdaTest {
	
	public static void main(String[] args) {
		//Lambda表达式比较常见的写法
		InterfaceTest it1 = (x) -> x * x;
		InterfaceTest it2 = x -> x * x;
		InterfaceTest it3 = (int x) -> x * x;
		InterfaceTest it4 = (x) -> {
			return x * x;
		};
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Hello World");
			}
		}).start();
		//匿名内部类语法糖
		new Thread(() -> System.out.println("Hello World")).start();
		
	}
}
interface InterfaceTest{
	
	int getValue(int number);
}
