package pers.allen.explore.juc;

/**
 * CAS(比较并交换)-CompareAndSwap
 * @author lengyul
 * @date 2018年12月19日 下午5:15:16
 * 基于CAS的算法称为 无锁定算法，因为线程不必再等待锁定，无论CAS操作成功还是失败，在任何一种情况中，它都在可预知的时间内完成。
 * 如果CAS失败，调用者可以重试CAS操作或采取其他适合的操作
 * 
 * CAS： 操作包含三个操作数 —— 内存位置(V)、预期原值(A)和新值(B)
 * 如果内存位置的值与预期原值相匹配，那么处理器会自动将该位置值更新为新值。否则，处理器不做任何操作
 * 
 * ABA问题：因为在更改 V 之前，CAS 主要询“V 的值是否仍为 A”，所以在第一次读取 V 以及对 V 执行 CAS 操作之前，
 * 如果将值从 A 改为 B，然后再改回 A，会使基于 CAS 的算法混乱。在这种情况下，CAS 操作会成功，但是在一些情况下，
 * 结果可能不是所预期的
 *
 */
public class CompareAndSwapMockTest {

	public static void main(String[] args) throws InterruptedException {
		SimulatedCAS cas = new SimulatedCAS();

		int value = cas.compareAndSwap(cas.getValue(), 1);
		System.out.println(value);
	}

	private static class SimulatedCAS {

		private volatile int value;

		public synchronized int getValue() {
			return value;
		}

		public synchronized int compareAndSwap(int expectedValue, int newValue) {
			int oldValue = value;
			if (oldValue == expectedValue) {
				value = newValue;
			}
			return oldValue;
		}

		public synchronized boolean compareAndSet(int expecteValue, int newValue) {

			return expecteValue == compareAndSwap(expecteValue, newValue);
		}

		public int increment() {
			int oldValue = value;
			while (compareAndSwap(oldValue, oldValue + 1) != oldValue) {
				oldValue = value;
			}
			return oldValue + 1;
		}

	}
}



