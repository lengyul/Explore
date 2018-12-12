package com.expolre.pattern.reactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorksThreadPool {
	
	private static ExecutorService worksThreadPool = Executors.newCachedThreadPool();
	
	public static void execute(Event event){
		worksThreadPool.execute(new Thread(() ->{
			System.out.println(Thread.currentThread().getName()+":"+event.getInputSource().toString());
		}));
	}
}
