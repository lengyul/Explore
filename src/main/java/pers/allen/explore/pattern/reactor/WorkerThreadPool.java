package pers.allen.explore.pattern.reactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkerThreadPool {
	
	private static final int availProcessors = Runtime.getRuntime().availableProcessors() * 2;
	private static ExecutorService executorService = Executors.newFixedThreadPool(availProcessors);	
	
	
	public static void execute(EventHandler eventHandler,Event event){
		//executorService.execute(new WorkerThread(eventHandler,event));
		executorService.execute(new WorkerThread(eventHandler, event));
	}
	
	private static class WorkerThread implements Runnable {
		EventHandler eventHandler;
		Event event;
		public WorkerThread(EventHandler eventHandler,Event event){
			this.eventHandler = eventHandler;
			this.event = event;
		}
		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(1);
				eventHandler.handle(event);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
