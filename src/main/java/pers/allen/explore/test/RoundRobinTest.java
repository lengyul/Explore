package pers.allen.explore.test;

import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinTest {

	private static final AtomicInteger idx = new AtomicInteger(0);
	private static String[] urls = new String[]{"url1","url2","url3"};
	
	private static int next() {
		if (isPowerOfTwo(urls.length))  // 如果为 偶数
			return idx.getAndIncrement() & urls.length - 1; // 
		return Math.abs(idx.getAndIncrement() % urls.length); // 
	}
		
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			// 0000 & 0011 = 0;
			// 0001 & 0011 = 1;
			// 0010 & 0011 = 2;
			// 0011 & 0011 = 3;
			// 0100 & 0011 = 0；
			// 0101 & 0011 = 1;
			// 0110 & 0011 = 2;
			System.out.println(i & 3);
		}
	}
	
	private static boolean isPowerOfTwo(int val) {
        return (val & -val) == val;
    }
	
}
