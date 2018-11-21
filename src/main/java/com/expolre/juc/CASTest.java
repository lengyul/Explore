package com.expolre.juc;

public class CASTest {
	
	public static void main(String[] args) {
		
		CompareAndSwap cas =new CompareAndSwap();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int expecteValue = cas.getValue();
					boolean flag = cas.compareAndSet(expecteValue,(int)(Math.random() * 9));
					System.out.println(flag);
				}
			}).start();
		}
	}
}

class CompareAndSwap{
	
	private int value;
	
	public synchronized int getValue() {
		return value;
	}
	
	public synchronized int compareAndSwap(int expecteValue,int newValue){
	
		int oldValue = value;
		if (oldValue == expecteValue) {
			this.value = newValue;
		}
		return oldValue;
	}
	
	public synchronized boolean compareAndSet(int expecteValue,int newValue){
		System.out.println(value);
		return expecteValue == compareAndSwap(expecteValue, newValue);
	}
}

