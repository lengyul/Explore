package pers.allen.explore.test;

import java.util.concurrent.TimeUnit;

/**
 * 递增共享计数器
 * @author lengyul
 * @date 2019年1月24日 下午5:34:10
 */
public class CounterTest {

	private int counter = 0;
	
	public int get() { return counter; }
	
	public void set(int n) { counter = n; }
	
	public void increment() {
		set(get() + 1);
	}
	
	public static void main(String[] args) throws InterruptedException {
		CounterTest ct = new CounterTest();
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				ct.increment();
			}).start();			
		}
		
		TimeUnit.SECONDS.sleep(3);
		
		System.out.println(ct.get());
	}
}
