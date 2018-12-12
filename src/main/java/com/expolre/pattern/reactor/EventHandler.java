package com.expolre.pattern.reactor;

/**
 * 事件处理器抽象类
 * @author lengyul
 * @date 2018年12月11日 下午4:22:12
 */
public abstract class EventHandler {
	
	//private InputSource inputSource;
	
	public abstract void handle(Event event); //各个事件具体类去实现

	/*public InputSource getInputSource() {
		return inputSource;
	}

	public void setInputSource(InputSource inputSource) {
		this.inputSource = inputSource;
	}*/
	
	
}
