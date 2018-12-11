package com.expolre.pattern.reactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Acceptor implements Runnable{

	private int port;
	private Selector selector;
	
	public Acceptor(int port,Selector selector){
		this.port = port;
		this.selector = selector;
	}
	
	private BlockingQueue<InputSource> sourceQueue = new LinkedBlockingQueue<>();
	
	//外部有输入请求后
	public void addNewConnection(InputSource source){
		sourceQueue.offer(source);
	}
	
	@Override
	public void run() {
		while (true) {
			
			InputSource source = null;
			try {
				source = sourceQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//如果有输入源
			if (source != null) {
				Event accpetEvent = new Event();
				accpetEvent.setInputSource(source);
				accpetEvent.setEventType(EventType.ACCPET);
				
				selector.addEvent(accpetEvent);
			}
			
		}
	}

}
