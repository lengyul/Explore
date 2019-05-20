package pers.allen.explore.thread.message;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程通信（根据指定key使线程进入等待或唤醒） 
 * 可能会存在一些问题
 * @author lengyul
 * @date 2019年1月24日 下午2:40:21
 * 
 */
public final class LockObjects {

	private static final AtomicInteger WAITING_THREAD_NUMBER = new AtomicInteger(0); // 等待线程数量
	private static final Map<String, Object> LOCKS = new HashMap<>(); // 存储标识和等待的对象
	
	// 如果使用线程安全的Map类代码则不需要考虑一些操作而添加同步
//	private static final Map<String, Object> SYNC_LOCKS = Collections.synchronizedMap(LOCKS);
 	
	private static final Object mutex = new Object(); // 互斥操作锁
	
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
		synchronized (mutex) { // 避免多线程put同一个key时value被覆盖，计数器被多次调用
			if (LOCKS.containsKey(key)) {
				throw new IllegalStateException("key exists already");
			} else if(LOCKS.containsValue(value)) {
				throw new IllegalStateException("value waiting already");
				/*synchronized (value) { // 如果同一个value多次wait，需要取出value并使用notifyAll()，未实现
					value.wait();
				}*/
			}
			LOCKS.put(key, value);
		}
		synchronized (value) {
			try {
				/**
				 * 如果这里线程安全完全不必要使用原子类，其他方法都保证了线程安全
				 */
				WAITING_THREAD_NUMBER.incrementAndGet(); 
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
		if (LOCKS.containsKey(key)) {
			removeAndNotifyObject(key);
		}
	}

	/**
	 * 唤醒所有正在等待的线程
	 */
	public static void notifyValues() {
		if (LOCKS.size() > 0) {
			synchronized (mutex) {
				for (Object item : LOCKS.values()) {
					synchronized (item) {
						WAITING_THREAD_NUMBER.decrementAndGet();
						item.notify();
					}
				}
				LOCKS.clear();
			}
		}
	}

	/**
	 * 根据key删除并唤醒locks中正在等待的线程
	 * 
	 * @param key
	 */
	private static void removeAndNotifyObject(String key) {
		synchronized (mutex) {
			Object value = LOCKS.remove(key);
			if (value != null) {
				synchronized (value) {
					WAITING_THREAD_NUMBER.decrementAndGet();
					value.notify();
				}
			}
		}
	}

	/**
	 * 当前locks中正在等待的线程数量
	 * 
	 * @return
	 */
	public static int getWaitingSize() {
		return WAITING_THREAD_NUMBER.get();
	}

}
