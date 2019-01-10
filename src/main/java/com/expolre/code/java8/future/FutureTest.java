package com.expolre.code.java8.future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * Future
 * @author lengyul
 * @date 2019年1月4日 下午3:30:09
 * JDK5新增了Future接口，用于描述一个异步计算的结果。
 * 	get方法返回结果的类型，对于结果的获取缺不是很方便，只能通过阻塞或者轮询的方式得到任务的结果。
 * -------------------------------------------------------------------------------
 * JDK8中的CompletableFuture实现了 Future ，CompletionStage ，它提供了非常强大的Future扩展功能。
 * 	简化异步编程的复杂性，提供了函数式编程的能力，可以通过回调函数的方式出来计算结果，
 * 也提供了转换和组合CompletableFuture的方法。
 * 对于阻塞或轮询的方式，依然可以通过 CompletableFuture 类的 CompletionStage 和 Future 接口方式支持。
 * 
 * get()：等待执行完成返回结果
 * getNow()：如果已完成，返回结果，否则返回指定的值IfAbsent
 * isDone()：true已完成
 * join()：完成后返回结果值，如果异常，返回异常
 */
public class FutureTest {


	@Test
	public void test(){
	//	completedFutureExample();
	//	runAsyncExample();
	//	thenApplyExample();
	//	thenApplyAsyncExample();
	//	thenApplyAsyncWithExecutorExample();
	//	thenAccpetExample();
		thenAccpetAsyncExample();
	}
	
	static Random random = new Random();
	static void randomSleep(){
		try {
			TimeUnit.SECONDS.sleep(random.nextInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 启动异步计算，getNow(null)返回计算结果或者 null
	 */
	static void completedFutureExample() {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
		assertTrue(cf.isDone());
		assertEquals("message", cf.getNow(null));
	}
	
	/**
	 * 简单异步场景
	 */
	static void runAsyncExample() {
		CompletableFuture<Void> cf = CompletableFuture.runAsync(() ->{
			assertTrue(Thread.currentThread().isDaemon());
			/*randomSleep();*/
		});
		assertFalse(cf.isDone());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(cf.isDone());
		assertTrue(cf.isDone());
	}
	
	/**
	 * 同步执行
	 */
	static void thenApplyExample() {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(
		s -> {
			assertFalse(Thread.currentThread().isDaemon());
			randomSleep();
			return s.toUpperCase();
		});
		System.out.println("--------会等待上面代码执行完成---------");
		assertEquals("MESSAGE",cf.getNow(null));
	}
	
	
	/**
	 * 异步执行
	 */
	static void thenApplyAsyncExample() {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(
		s -> {
			assertTrue(Thread.currentThread().isDaemon());
			randomSleep();
			return s.toUpperCase();
		});
		System.out.println("--------------直接执行------------------");
		assertNull(cf.getNow(null));
		//assertNotNull(cf.getNow(null)); error，因为还未cf执行完成
		//assertEquals("MESSAGE",cf.getNow(null)); error，因为还未cf执行完成
		assertEquals("MESSAGE", cf.join());
	} 
	
	
	/**
	 * 使用固定的线程池完成异步执行
	 */
	static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
		int count = 1;
		@Override
		public Thread newThread(Runnable runnable) {
			return new Thread(runnable, "custom-executor-" + count++);
		}
	});
	static void thenApplyAsyncWithExecutorExample() {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
			assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
			assertFalse(Thread.currentThread().isDaemon());
			randomSleep();
			return s.toUpperCase();
		}, executor);
		assertNull(cf.getNow(null));
		assertEquals("MESSAGE", cf.join());
	}
	
	/**
	 * 同步执行消费者
	 */
	static void thenAccpetExample() {
		StringBuilder result = new StringBuilder();
		CompletableFuture.completedFuture("thenAccpet messgae").thenAccept(s -> result.append(s));
		assertTrue("Result was empty", result.length() > 0);
	}
	
	/**
	 * 异步执行消费者
	 */
	static void thenAccpetAsyncExample() {
		StringBuilder result = new StringBuilder();
		CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
				.thenAcceptAsync(s -> result.append(s));
		cf.join();
		assertTrue("Result was empty", result.length() > 0);
	}
}
