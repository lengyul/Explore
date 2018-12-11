package com.expolre.pattern.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 事件轮询器
 * @author lengyul
 * @date 2018年12月11日 下午4:36:29
 */
public class Selector {
	
	//链表阻塞实现缓冲队列，保证线程安全
	private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
	private Object lock = new Object();
	
	
	List<Event> select(){
		if (eventQueue.isEmpty()) {
			synchronized (lock) {
				if (eventQueue.isEmpty()) {
					try {
						System.out.println("Selector----------------------wait()");
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		List<Event> events = new ArrayList<>();
		eventQueue.drainTo(events); //从该队列中删除所有可用的元素，并将它们添加到给定的集合中
		return events;
	}
	
	public void addEvent(Event e){
		boolean result = eventQueue.offer(e);
		if (result) {
			synchronized (lock) {
				System.out.println("Selector----------------------notify()");
				lock.notifyAll();
			}
		}
	}
	
}
