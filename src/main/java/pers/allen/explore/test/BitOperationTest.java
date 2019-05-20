package pers.allen.explore.test;

public class BitOperationTest {
	
	public static void main(String[] args) {
		
	//	1. 判断奇偶数
		// 1010 & 0001 = 0001 
		// 0011 & 0001 = 0010
//		System.out.println(3 & 1);
		int number = 20;
		if((number & 1) == 1) {
			System.out.println("奇数");
		} else {
			System.out.println("偶数");
		}
	
	// 2.交换两个数	
		int x = 1,y = 2;
		/*int tem = x;
		x = y;
		y = tem;*/
		x = x ^ y; // 0011 = 0001 ^ 0010 
		y = x ^ y; // 0001 = 0011 ^ 0010
		x = x ^ y; // 0010 = 0011 ^ 0001
		System.out.println(x + " " + y);
		
		System.out.println(x << 2);
	}
}
