package com.expolre.pattern.observer.wechat;

import java.util.Observable;

/**
 * jdk自带观察者模式
 * 缺点：
 * 1.Observable是一个类，而不是一个接口，导致Observable类的扩展性不高，不灵活
 * 2.某些方法setChanged() clearChanged()为protected，这意味着除非继承自Observable，否则有关方法不能调用，无法通过组合
 * 的方式获得Observable类的功能
 * @author lengyul
 *
 */
public class WeChatServer extends Observable {
		
	private String message; 
	
	public void setInformation(String message) {
		this.message = message;
		this.setChanged();
		this.notifyObservers();
	}
	
	public String getMessage() {
		return message;
	}
	
}
