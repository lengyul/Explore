package com.expolre.pattern.reactor;

/**
 * ACCPET事件处理器
 * @author lengyul
 * @date 2018年12月11日 下午4:28:54
 */
public class AccpetEventHandler extends EventHandler {

	
	
	@Override
	public void handle(Event event) {
		//处理Accpet事件
		if (event.getEventType() == EventType.ACCPET) {
			System.out.println("--------------------ACCPET");
			//设置READ事件
			Event readEvent = new Event();
			readEvent.setEventType(EventType.READ);
			readEvent.setInputSource(event.getInputSource());
		}
		
	}

}
