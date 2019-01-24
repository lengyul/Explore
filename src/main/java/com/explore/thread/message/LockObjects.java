package com.explore.thread.message;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程通信（根据指定key来等待或唤醒）
 * 可能会存在一些问题
 * @author lengyul
 * @date 2019年1月24日 下午2:40:21
 */
public final class LockObjects {

	static AtomicInteger waitingSize = new AtomicInteger(0);

	private LockObjects() {
	}

	static Map<String, Object> locks = new Hashtable<>();
	public static void wait(String key) {
		wait(key, new Object());
	}

	public static void wait(String key, Object value) {
		wait(key, value, 0);
	}

	public static void wait(String key, Object value, long timeout) {
		if (locks.containsKey(key) || locks.containsValue(value)) {
			System.out.println(key + "\t" + value);
			return;
		}
		synchronized (value) {
			locks.put(key, value);
			try {
				waitingSize.getAndIncrement();
				value.wait(timeout);
			//	System.out.println("-------------------end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void notify(String key) {
		if (locks.containsKey(key)) {
			Object value = locks.get(key);
			synchronized (value) {
				waitingSize.getAndDecrement();
				value.notifyAll();
			}
		}
	}

	public static void notifyValues() {
		//System.out.println("size:" + locks.size());
		if (locks.size() > 0) {
			for (Object item : locks.values()) {
				synchronized (item) {
					waitingSize.getAndDecrement();
					item.notifyAll();
				}
			}
			/*locks.values().forEach(obj -> {
				synchronized (obj) {
					waitingSize.getAndDecrement();
					obj.notifyAll();
				}
			});*/
		}
	}

	public static int getWaitingSize() {
		return waitingSize.get();
	}

}
