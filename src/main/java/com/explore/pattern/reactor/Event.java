package com.explore.pattern.reactor;

/**
 * reactor模式中内部处理的事件类
 * @author lengyul
 * @date 2018年12月11日 下午4:19:44
 */
public class Event {
	
	private InputSource inputSource;
	private EventType eventType;
	public InputSource getInputSource() {
		return inputSource;
	}
	public void setInputSource(InputSource inputSource) {
		this.inputSource = inputSource;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	
	
}
