package com.explore.code;

import static org.junit.Assert.fail;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

public class CodeTest {

	
	public static void main(String[] args) {
		
		
		Queue<String> queue = new ConcurrentLinkedQueue<>();
		for (int i = 0; i < 1000; i++) {
			queue.offer("a"+i);
		}
		System.out.println("-----------------------");
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!queue.isEmpty()) {
					String str = queue.poll();
					System.out.println(Thread.currentThread().getName()+":"+str);
				}	
			}
		}).start();

		new Thread(new Runnable() {	

			@Override
			public void run() {
				while (!queue.isEmpty()) {
					String str = queue.poll();
					System.out.println(Thread.currentThread().getName()+":"+str);
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!queue.isEmpty()) {
					String str = queue.poll();
					System.out.println(Thread.currentThread().getName()+":"+str);
				}
			}
		}).start();
		
		
		/*new Thread(() -> {
			for (int i = 0; i < queue.size(); i++) {				
				String str = queue.poll();
				System.out.println(str);
			}
		}).start();
		
		new Thread(() -> {
			for (int i = 0; i < queue.size(); i++) {				
				String str = queue.poll();
				System.out.println(str);
			}
		}).start();
		
		new Thread(() -> {
			for (int i = 0; i < queue.size(); i++) {				
				String str = queue.poll();
				System.out.println(str);
			}
		}).start();*/
		
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
