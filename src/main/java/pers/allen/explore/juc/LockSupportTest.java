package pers.allen.explore.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现了线程阻塞和解除线程阻塞的功能（线程通信）
 * @author lengyul
 * @mock  LockObjects
 * @date 2019年6月10日 下午2:18:30
 * 
 * 与 Object（wait/notify） 相比，LockSupport（park/unpark） 不用包含在同步代码块中里，不需要抛出异常
 */
public class LockSupportTest {
	
	public static void main(String[] args) throws InterruptedException {

		// 线程阻塞
//		Thread thread = new Thread(() -> LockSupport.park());
		Object object = new Object(); // 指定同步对象
		Thread thread = new Thread(() -> LockSupport.park(object));
		thread.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		// 解除线程阻塞，需要指定 Thread thread
		LockSupport.unpark(thread);
		// 获取同步对象
		Object obj = LockSupport.getBlocker(thread);
		
		System.out.println(obj == object);
	}

	
}
