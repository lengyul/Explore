package com.expolre.pattern.reactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件分发器
 * @author lengyul
 * @date 2018年12月11日 下午4:30:45
 */
public class Dispatcher {

	Selector selector;
	
	public Dispatcher(Selector selector){
		this.selector = selector;
	}
	
	Map<EventType,EventHandler> eventHandlerMap = new ConcurrentHashMap<>();
	
	public void registerEventHandler(EventType eventType,EventHandler eventHandler){
		eventHandlerMap.put(eventType, eventHandler);
	}
	
	public void removeEventHandler(EventType eventType){
		eventHandlerMap.remove(eventType);
	}
	
	public void handleEvents() {
        dispatch();
    }
	
	public void dispatch(){
		while (true) {
			System.out.println("Dispatcher--------------------------dispatch()");
			List<Event> events = selector.select(); //该方法阻塞直到有事件到来
			System.out.println("The current events size:"+events.size());
			for (Event event : events) {
				EventHandler eventHandler = eventHandlerMap.get(event.getEventType());
				// eventHandler.handle(event);
				WorkerThreadPool.execute(eventHandler, event);
			}
		}
	}
	
}
