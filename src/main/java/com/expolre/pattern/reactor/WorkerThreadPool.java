package com.expolre.pattern.reactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkerThreadPool {
	
	private static final int availProcessors = Runtime.getRuntime().availableProcessors();
	private static ExecutorService executorService = Executors.newFixedThreadPool(availProcessors);	
	Thread workerThread = new Thread(new WorkerThread());
	
	
	public void execute(Event event){
		executorService.execute(workerThread);
	}
	
	private class WorkerThread implements Runnable {
		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println(Thread.currentThread().getName()+"："+event.getInputSource().toString());
		}
	}
	
	
	
}
