package pers.allen.explore.thread.message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import pers.allen.explore.utils.RandomUtils;

public class MockTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();

		final String key = RandomUtils.randomNumeric(10);
		es.execute(() -> {
				LockObjects.wait(key);
				System.out.println(LockObjects.getWaitingSize());
		});
		TimeUnit.SECONDS.sleep(1);
		
		for (int i = 0; i < 100; i++) {			
			es.execute(() -> {
				LockObjects.notify(key);
			});			
		} 
		
		for (int i = 0; i < 100; i++) {
		es.execute(() -> {
					LockObjects.wait(key);
				});			
		}
		es.shutdown();
	}
}
