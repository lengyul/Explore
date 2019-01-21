package com.explore.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列：是一个在队列基础上又支持了两个附加操作的队列，阻塞队列常用于生产者和消费者的场景。
 * 支持阻塞的插入方法：队列满时，队列会阻塞插入元素的线程，直到队列的size < capacity
 * 支持阻塞的获取/移除方法：队列的size为0时，获取元素的线程会被阻塞直到队列的size > 0
 * @author lengyul
 * @date 2018年12月12日 上午10:37:23
 * JDK7 提供了7个阻塞队列：
 * 1.ArrayBlockingQueue  数组结构组成的有界阻塞队列
 * 2.LinkedBlockingQueue 链表结构组成的有界阻塞队列(Integer.MAX_VALUE)
 * 3.PriorityBlockingQueue 支持优先级的无界阻塞队列
 * 4.DelayQueue 支持延时获取元素的无界阻塞队列，即可指定多久才能从队列中获取当前元素
 * 5.SynchronousQueue 不存储元素的阻塞队列，每一个put必须等待一个take操作，否则不能继续添加其他操作
 * 6.LinkedTransferQueue 链表结构组成的无界阻塞队列
 * 7.LinkedBlockingDeque 链表结构组成的双向阻塞队列，优势在于多线程入队时，减少一半的竞争
 */
public class BlockingQueueTest {
	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<String> bq = new LinkedBlockingQueue<>(); //defalut size Integer.MAX_VALUE
		
		//添加元素
		bq.put("a"); //当队列元素满时，等待
		boolean addResult = bq.add("b"); //直接返回结果
		boolean offerResult = bq.offer("c"); //直接返回结果
		System.out.println("add:"+addResult+"\t"+"offer:"+offerResult);
		System.out.println("size:"+bq.size());
		
		//获取删除元素
		String str = null;
		str = bq.take(); //获取并删除队列的头元素，当队列元素为0时，等待
		str = bq.poll(); //获取并删除队列的头元素，当队列元素为0时，返回null
		str = bq.peek(); //获取但不删除队列的头元素，当队列元素为0时，返回null
		str = bq.element(); //获取但不删除队列的头元素，当队列元素为0时，返回 java.util.NoSuchElementException
		System.out.println(str);
		
		List<String> lists = new ArrayList<>();
		bq.drainTo(lists); //队列中所有可用的元素添加到指定集合中并删除
		
		System.out.println("size:"+bq.size());
	}
}
