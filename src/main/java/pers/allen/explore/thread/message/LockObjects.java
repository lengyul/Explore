package pers.allen.explore.thread.message;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程通信（根据指定key使线程进入等待或唤醒） 可能会存在一些问题
 * 
 * @author lengyul
 * @date 2019年1月24日 下午2:40:21
 * 
 */
public final class LockObjects {

	private static AtomicInteger waitingNumber = new AtomicInteger(0); // 等待线程数量
	private static Map<String, Object> locks = new HashMap<>();

	private LockObjects() {
	}

	public static void wait(String key) {
		wait(key, new Object());
	}

	public static void wait(String key, Object value) {
		wait(key, value, 0);
	}

	/**
	 * 当前线程进入等待（阻塞）状态
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public static void wait(String key, Object value, long timeout) {
		synchronized (locks) { // 避免多线程put同一个key时value被覆盖，计数器被多次调用
			if (locks.containsKey(key) || locks.containsValue(value)) {
				throw new IllegalStateException("key or value exists already");
			}
			locks.put(key, value);
		}
		synchronized (value) {
			try {
				waitingNumber.incrementAndGet();
				value.wait(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据key唤醒正在等待的线程
	 * 
	 * @param key
	 */
	public static void notify(String key) {
		if (locks.containsKey(key)) {
			removeAndNotifyObject(key);
		}
	}

	/**
	 * 唤醒所有正在等待的线程
	 */
	public static void notifyValues() {
		if (locks.size() > 0) {
			synchronized (locks) {
				for (Object item : locks.values()) {
					synchronized (item) {
						waitingNumber.decrementAndGet();
						item.notify();
					}
				}
				locks.clear();
			}
		}
	}

	/**
	 * 根据key删除并唤醒locks中正在等待的线程
	 * 
	 * @param key
	 */
	private static void removeAndNotifyObject(String key) {
		Object value = locks.remove(key);
		if (value != null) {
			synchronized (value) {
				waitingNumber.decrementAndGet();
				value.notify();
			}
		}
	}

	/**
	 * 当前locks中正在等待的线程数量
	 * 
	 * @return
	 */
	public static int getWaitingSize() {
		return waitingNumber.get();
	}

}
