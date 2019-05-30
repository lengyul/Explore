package pers.allen.explore.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class ListHelper<E> {
	
	public List<E> list = Collections.synchronizedList(new ArrayList<>());
	
	/**
	 *   使用了外部的锁，意味着该方法对于 list 的其他操作来说并不是原子的，不能确保调用 putIfAbsent 执行时
	 * 另一个线程执行 list 的其他操作方法
	 * @param x
	 * @return
	 */
	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !list.contains(x);
		if (absent) {
			list.add(x);
		}
		return absent;
	}
	
	/**
	 *  mutex 为互斥锁指向当前 list，synchronized (list) 与 List 中的操作为同一把锁，保证原子性
	 * @see SynchronizedList(List<E> list) -> super(list) -> mutex = this;
	 * @param x
	 * @return
	 */
	public boolean putIfAbsent2(E x) {
		synchronized (list) {
			boolean absent = !list.contains(x);
			if (absent) {
				list.add(x);
			}
			return absent;
		}
	}

}
