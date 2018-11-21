package com.expolre.pattern.strategy;

import org.junit.Test;

/**
 * 策略模式：
 * 重点在于：给对象参入什么样的策略，就执行对应的行为
 * 优点在于：可以轻易的扩展与改变策略，动态改变对象的行为
 * 确定在于：必须要知道所有的策略类，并根据行为决定使用哪一种；每一个具体的行为都会产生一个实现类，这样会造成策略类过多
 * @author lengyul
 *
 */
public class DuckTest {

	/*
	 * 橡皮鸭测试
	 */
	@Test
	public void rubberDuckTest() {
		
		RubberDuck rubberDuck =new RubberDuck();
		//
		rubberDuck.display();
		//未设置任何行为
		rubberDuck.performFly();
		rubberDuck.performQuack();
		//设置飞行行为和叫声行为
		rubberDuck.setFlyBehavior(new FlyNoWay());
		rubberDuck.setQuackBehavior(new Quack());
		rubberDuck.performFly();
		rubberDuck.performQuack();
		
	}
	
	/*
	 * 红头鸭测试
	 */
	@Test
	public void readheadDuckTest() {
		
		ReadheadDuck readheadDuck =new ReadheadDuck();
		//
		readheadDuck.display();
		//未设置任何行为
		readheadDuck.performFly();
		readheadDuck.performQuack();
		//设置飞行行为和叫声行为
		readheadDuck.setFlyBehavior(new FlyWithWings());
		readheadDuck.setQuackBehavior(new Squeak());
		readheadDuck.performFly();
		readheadDuck.performQuack();
		
	}	
}
