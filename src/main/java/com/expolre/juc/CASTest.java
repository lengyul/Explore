package com.expolre.juc;

/**
 * 比较并交换(CAS)
 * @author lengyul
 * @date 2018年12月19日 下午5:15:16
 * 基于CAS的算法称为 无锁定算法，因为线程不必再等待锁定，无论CAS操作成功还是失败，在任何一种情况中，它都在可预知的时间内完成。
 * 如果CAS失败，调用者可以重试CAS操作或采取其他适合的操作
 * CAS 操作包含三个操作数 —— 内存位置(V)、预期原值(A)和新值(B)。
 * 如果内存位置的值与预期原值相匹配，那么处理器会自动将该位置值更新为新值。否则，处理器不做任何操作。
 */
public class CASTest {
	
	public static void main(String[] args) {
		
		SimulatedCAS cas = new SimulatedCAS();
		
		for (int i = 0; i < 10; i++) {
			new Thread(() ->{
				System.out.println(cas.increment());
			}).start();
		}
		
		/*for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int expectedValue = cas.getValue();
					boolean flag = cas.compareAndSet(expectedValue,(int)(Math.random() * 9));
					System.out.println(flag);
				}
			}).start();
		}*/
	}
}

class SimulatedCAS{
	
	private int value;
	
	public synchronized int getValue() {
		return value;
	}
	
	public synchronized int compareAndSwap(int expectedValue,int newValue){
		
		int oldValue = value;
		if (oldValue == expectedValue) {
			value = newValue;
		}
		return oldValue;
	}
	
	public synchronized boolean compareAndSet(int expecteValue,int newValue){

		return expecteValue == compareAndSwap(expecteValue, newValue);
	}
	
	public int increment(){
		int oldValue = value;
		while (compareAndSwap(oldValue, oldValue + 1) != oldValue) {
			oldValue = value;
		}
		return oldValue + 1;
	}
	
}

