package com.expolre.thread.sync;

/**
 * Java语言关键字-->synchronized(同步锁)
 * @author lengyul
 * @date 2018年12月20日 下午3:31:18
 * 保证原子性(monitorenter / monitorexit)和可见性(释放锁之前更新主内存，获取锁时重新去读取主存数据)
 * 意味着同一时刻保证只有一个线程去执行被synchronized锁定的代码。
 * 两种用法：
 * 	1.对象锁：方法（锁对象默认为this）和同步代码块
 *  2.类锁：静态方法和锁Class对象
 */
public class SynchronizedTest {
	//对象锁
	public synchronized void test1(){} //默认为this
	public void test2(){
		synchronized (this) { //手动指定类对象
		}
	}
	
	//类锁
	public static synchronized void test3(){}
	public void test4(){
		synchronized (SynchronizedTest.class) {
		}
	}
	

}
