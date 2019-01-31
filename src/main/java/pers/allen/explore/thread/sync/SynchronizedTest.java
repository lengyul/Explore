package pers.allen.explore.thread.sync;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Java语言关键字-->synchronized(同步锁)
 * @author lengyul
 * @date 2018年12月20日 下午3:31:18
 * 保证原子性(monitorenter / monitorexit)和可见性(释放锁之前更新主内存，获取锁时重新去读取主存数据)
 * 互斥：意味着同一时刻保证只有一个线程去执行被synchronized锁定的代码。
 * 可见性：确保了一个线程更改的数据对于其他线程是可见的。
 * 两种用法：
 * 	1.对象锁：方法（锁对象默认为this）和同步代码块
 *  2.类锁：静态方法和锁Class对象
 */
public class SynchronizedTest {
	//对象锁
	public synchronized void test1(){
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println(Instant.now());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//默认为this
	public void test2(){
		synchronized (this) { //手动指定类对象
		}
	}
	
	//类锁
	public static synchronized void test3(){
		try {
			TimeUnit.SECONDS.sleep(6);
			System.out.println(Instant.now());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void test4(){
		synchronized (SynchronizedTest.class) {
		}
	}
	
	public static void test5(){
		System.out.println("test 5");
	}
	
	public static void main(String[] args) throws InterruptedException {
		SynchronizedTest st1 = new SynchronizedTest();
		SynchronizedTest st2 = new SynchronizedTest();
		
		System.out.println(Instant.now());
		new Thread(() ->{
			test3(); //sleep 6s
		}).start();
		/*
		new Thread(() ->{			
			test5();//sleep 3s
		}).start();*/
		
		
		
		new Thread(() ->{			
			st1.test1();//sleep 3s
		}).start();
		
		new Thread(() ->{			
			st2.test1();//sleep 3s
		}).start();
		
	}
	
}
