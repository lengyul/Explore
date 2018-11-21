package com.expolre.juc;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：在完成某些线程时，只有其他所有线程完成时，当前线程才继续执行
 * @author lengyul
 *
 */
public class CountDownLatchTest {
		
	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch latch =new CountDownLatch(5);
		LatchDemp ld =new LatchDemp(latch);
		
		Instant start = Instant.now();
		for (int i = 0; i < 5; i++) {
			new Thread(ld).start();
		}
		
		latch.await();
		
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);
		System.out.println("任务耗费时间："+duration.getSeconds()+"s"); 
	}
}

class LatchDemp implements Runnable{
	
	private CountDownLatch latch;
	
	public LatchDemp(CountDownLatch latch){
		this.latch = latch;
	}
	
	@Override
	public void run() {
		synchronized (this) {
		try {			
			for (int i = 0; i < 50000; i++) {
				System.out.println(i);
			}
		}finally{			
			latch.countDown();// - 1
			}
		}
	}
	
}
