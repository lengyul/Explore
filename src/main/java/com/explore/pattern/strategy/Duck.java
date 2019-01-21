package com.explore.pattern.strategy;

public abstract class Duck {
	
	private FlyBehavior flyBehavior;
	
	private QuackBehavior quackBehavior;
	
	/*
	 * 共同的游泳行为
	 */
	public void swim() {
		System.out.println("I am swimming");
	}
	
	/*
	 * 各自的表现行为
	 */
	public abstract void display();
	
	/*
	 * 不同的飞行行为
	 */
	public void performFly(){
		if (flyBehavior == null) {
			System.out.println("[performFly]Please set the behavior");
			return;
		}
		flyBehavior.fly();
	}
	
	/*
	 * 不同的叫行为
	 */
	public void performQuack(){
		if (quackBehavior == null) {
			System.out.println("[performQuack]Please set the behavior");
			return;
		}
		quackBehavior.quack();
	}
	
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}
	
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}
	
}
