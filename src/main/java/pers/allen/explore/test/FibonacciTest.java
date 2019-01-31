package pers.allen.explore.test;

/**
 * 斐波那契数列
 * @author lengyul
 * @date 2018年12月10日 下午5:17:54
 */
public class FibonacciTest {
	public static void main(String[] args) {
		// 1 1 2 3 5 8 13 21
		System.out.println(fib(5));
	}
	public static long fib(int n){
		if (0 == n) {
			return 0;
		}
		if (1 == n) {
			return 1;
		}
		return fib(n-1) + fib(n-2);
	}
		/*
		 *  fib(4)+fib(3)
		 * 	   fib(3)+fib(2) + fib(2)+fib(1)/1
		 * 		  fib(2)+fib(1)/1 + fib(1)+ 0 + fib(1) + 0 + 1	
		 *			fib(1)+fib(0)
		 *  
		 *  --------------------------------------------------
		 *  
		 *   fib(4)
		 *   	fib(3)+fib(2)
		 *   		fib(2)+fib(1) + fib(1)+fib(0) +1+1
		 *   			fib(1) +1
		 *   			= 2;
		 *   
		 *   fib(3)
		 *   	fib(2)+fib(1)     +1
		 *   		fib(1)+fib(0) +1
		 *   		= 2;
		 *   			
		 */
	
}
