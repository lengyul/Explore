package pers.allen.explore.code.java8.future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
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
 * get方法返回结果的类型，对于结果的获取缺不是很方便，只能通过阻塞或者轮询的方式得到任务的结果。
 * -------------------------------------------------------------------------------
 * JDK8中的CompletableFuture实现了 Future ，CompletionStage ，它提供了非常强大的Future扩展功能。
 * 简化异步编程的复杂性，提供了函数式编程的能力，可以通过回调函数的方式出来计算结果，
 * 也提供了转换和组合CompletableFuture的方法。
 * 对于阻塞或轮询的方式，依然可以通过 CompletableFuture 类的 CompletionStage 和 Future 接口方式支持。
 * 
 * get()：等待执行完成返回结果
 * getNow()：如果已完成，返回结果，否则返回指定的值
 * isDone()：true已完成
 * join()：完成后返回结果值，如果异常，返回异常
 * complete()：如果任务未完成，任务结果get()方法的值为指定的值
 */
public class FutureTest {

	private static CountDownLatch control = new CountDownLatch(5);
	private static void await() {
		try {
			control.await(); // 阻塞主线程
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	private static Random random = new Random();
	private static void randomSleep(){
		try {
			TimeUnit.SECONDS.sleep(random.nextInt(10)); // 随机睡眠
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * CompletableFuture中的异步操作方法如果没有指定Executor，
	 * 默认会使用ForkJoinPool.commonPool()作为它的线程池执行异步操作
	 * runAsync(Runnable runnable)  无返回值
	 * supplyAsync(Supplier<U> supplier) 支持返回值
	 */
	@Test
	public void test() {
	//	completedFutureExample();
	//	runSupplyAsyncExample();
	//	runAsyncExample();
	//	thenAccpetExample(); // thenRun()类似，但是没有入参，不关心任务的处理结果
	//	thenAccpetAsyncExample(); 
	//	handleExample(); 
	//	whenCompleteExample();
	//	thenCombineExample();
	//	thenApplyToEitherExample();
	//	thenComposeExample();
	//	thenApplyAsyncWithExecutorExample();
		
	}
	
	/**
	 * 返回一个新的CompletableFuture用来执行，并指定value
	 */
	static void completedFutureExample() {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
		assertTrue(cf.isDone());
		assertEquals("message", cf.getNow(null));
	}
	
	/**
	 * 简单异步场景（无返回值）
	 */
	static void runAsyncExample() {
		CompletableFuture<Void> cf = CompletableFuture.runAsync(() ->{
			assertTrue(Thread.currentThread().isDaemon());
			/*randomSleep();*/
		});
		assertFalse(cf.isDone());
		try {
			TimeUnit.SECONDS.sleep(2); // 主线程睡眠等待异步线程执行完毕
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(cf.isDone());// 异步任务是否完成
		assertTrue(cf.isDone());
	}
	
	/**
	 * 简单异步场景（有返回值）
	 */
	static void runSupplyAsyncExample() {
		
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			return "0011";
		});
		while (true) {
			if (cf.isDone()) {
				System.out.println(cf.getNow(null));
				break;
			}
		}
	}
	

	/**
	 * 同步执行消费者（任务依赖）
	 */
	static void thenAccpetExample() {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " supplyAsync()");
			return 1;
		});
		cf.thenAccept((v) -> { // 等待上一个任务执行完成后执行，无返回值
			System.out.println(Thread.currentThread().getName() + " thenAccept()" + v);
		});
		
		/*cf.thenRun(() -> { // 等待上一个任务执行完成后执行，无参无返回值
			System.out.println(Thread.currentThread().getName() + " thenAccept()" + v);
		});*/
		
	}
	
	/**
	 * 异步执行消费者
	 * @throws InterruptedException 
	 */
	static void thenAccpetAsyncExample() {
		StringBuilder result = new StringBuilder();
		CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
				.thenAcceptAsync(s -> {result.append(s);
						System.out.println(Thread.currentThread().getName() + "异步线程执行------------");
				});
		System.out.println(Thread.currentThread().getName() + "主线程执行------------");
		//assertNotNull(cf.getNow(null)); error，未执行完成
		//assertEquals("MESSAGE",cf.getNow(null)); error，未执行完成
		cf.join(); 
		assertTrue("Result was empty", result.length() > 0);
	}
	
	/**
	 * handle用于任务执行完成时的结果处理
	 * handle接收两个参数，value和exception，处理数据和异常
	 */
	static void handleExample() {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			return 0;
		}).handle((v,e) -> { // 等待上一个任务执行完成后执行，有返回值
			System.out.println(v);
			return v + 1;
		});
			/*.handleAsync((v,e) -> {
			System.out.println(v);
			return v + 1;
		});*/
		System.out.println(cf.getNow(null)); // 获取最终结果
	}
	
	/**
	 * 回调方法（当计算结果完成时/抛出异常）
	 * whenComplete：在当前任务完成后执行
	 * whenCompleteAsync：任务提交到线程池中执行
	 */
	static void whenCompleteExample() {
		CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " async execution completed...");
			randomSleep();
			return 0;
		}).whenComplete((v,e) -> { // 函数式接口 BiConsumer<T, U, R>， T 和 U 分别是两个参数的类型，输出类型 R
			System.out.println(Thread.currentThread().getName() + " 异步执行结果：" + v);
		}).exceptionally(e -> 1); //  函数式接口 Function<T, R> 输入类型 T，输出类型 R
		await();
	}
	
	/**
	 * 任务合并（两个CompletableFuture的任务执行完成后，结果进行处理）
	 */
	static void thenCombineExample() {
		CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
			return 1;
		});
		CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
			return 2;
		});
		CompletableFuture<Integer> result = cf1.thenCombine(cf2, (x,y) -> {
			return x + y;
		});
		/*cf1.runAfterBoth(cf2,() -> {
			// do something
		});*/
		System.out.println(result.getNow(null));
	}
	
	
	/**
	 * 任务优先（获取执行返回最快的任务结果）
	 */
	static void thenApplyToEitherExample() {
		CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 1;
		});
		CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
			return 2;
		});
		
		// 有返回值
		/*cf1.applyToEither(cf2, x -> {
			return x;
		});*/
		cf1.acceptEither(cf2, (x) -> {
			System.out.println(x);
		});
		/*cf1.runAfterEither(cf2, () -> {
			// do something
		});*/
		await();
	}
	
	/**
	 * 任务组合（连接两个CompletableFuture）
	 */
	static void thenComposeExample() {
		CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
			return 1;
		});
		CompletableFuture<Integer> cf2 = cf1.thenCompose((x) -> {
			return CompletableFuture.supplyAsync(() -> {
				return x + 1;
			});
		});
		
		System.out.println(cf2.getNow(null));
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
}
