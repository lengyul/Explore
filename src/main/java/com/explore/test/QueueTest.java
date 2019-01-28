package com.explore.test;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * 队列是一种特殊的线性表，它只允许在表的前端进行删除操作，而在表的后端进行插入操作。
 * LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用。
 * @author lengyul
 *
 */
public class QueueTest {

	@Test
	public void testQueue(){
			
		Queue<String> queue =new LinkedList<>();
		
		queue.offer("a");
		queue.offer("b");
		queue.offer("c");
		queue.offer("d");
		queue.offer("e");
		
		for (String str : queue) {
			System.out.println(str);
		}
		
		//System.out.println("poll="+queue.poll()); //返回第一个元素，并在队列中删除
		System.out.println("element="+queue.element()); //返回第一个元素 
		System.out.println("peek="+queue.peek()); //返回第一个元素 
	}
}
