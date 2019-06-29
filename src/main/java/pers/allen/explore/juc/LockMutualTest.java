package pers.allen.explore.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程按序交替执行
 * 条件锁
 * @author lengyul
 *
 */
public class LockMutualTest {

	public static void main(String[] args) {
		LockMutualApp lmd = new LockMutualApp();
			
		
		  new Thread(() -> { for (int i = 1; i <= 100; i++) { try { lmd.printA(i);
		  } catch (Exception e) { e.printStackTrace(); } } }).start();;
		  
		  new Thread(() -> { for (int i = 1; i <= 100; i++) { try { lmd.printB(i);
		  } catch (Exception e) { e.printStackTrace(); } } }).start();;
		  
		  new Thread(() -> { for (int i = 1; i <= 100; i++) { try { lmd.printC(i);
		  } catch (Exception e) { e.printStackTrace(); } } }).start();;
	}
	
	private static class LockMutualApp {

		private int  number = 1;

		private Lock lock = new ReentrantLock();

		private Condition condition1 = lock.newCondition();
		private Condition condition2 = lock.newCondition();
		private Condition condition3 = lock.newCondition();

		public void printA(int count){
			lock.lock();
			try {
				while (number != 1) {
					condition1.await();
				}
				System.out.println("当前线程：" + Thread.currentThread().getName() + "打印数据：" + count);
				number = 2;
				condition2.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public void printB(int count){
			lock.lock();
			try {
				while (number != 2) {
					condition2.await();
				}
				System.out.println("当前线程：" + Thread.currentThread().getName() + "打印数据：" + count);
				number = 3;
				condition3.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public void printC(int count){
			lock.lock();
			try {
				while (number != 3) {
					condition3.await();
				}
				System.out.println("当前线程：" + Thread.currentThread().getName() + "打印数据：" + count);
				number = 1;
				condition1.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

	}

}

