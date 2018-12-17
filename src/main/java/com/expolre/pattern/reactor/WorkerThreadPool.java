package com.expolre.pattern.reactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerThreadPool {
	
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	
	public static void execute(Event event){
		executorService.execute(new Thread(() ->{
			System.out.println(Thread.currentThread().getName()+":"+event.getInputSource().toString());
		}));
	}
}
