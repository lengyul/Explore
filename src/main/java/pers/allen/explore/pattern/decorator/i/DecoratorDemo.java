package pers.allen.explore.pattern.decorator.i;

import org.junit.Test;

/**
 * Decorator装饰器，
 * 装饰器通过包装一个装饰对象来扩展其功能，而又不改变其接口，这实际上是基于对象的适配器模式的一种变种。
 * 它与对象的适配器模式的异同点如下。
 * 相同点：都拥有一个目标对象。
 * 不同点：适配器模式需要实现另外一个接口，而装饰器模式必须实现该对象的接口。
 * @author lengyul
 *
 */
public class DecoratorDemo {

	@Test
	public void prints(){
		
		//string方法输出
		Sourcable sourcable = new Source();
		String str = sourcable.print("hello,world!");
		System.out.println(str);
		System.out.println("------------------------------");
		//增强大写
		sourcable = new SourceUpperCase(sourcable);
		 	   str = sourcable.print("hello,world!");
		System.out.println(str);
		System.out.println("------------------------------");			
	}
	
	@Test
	public void print(){
		//void方法输出
		/*Sourcable sourcable = new Source();
		sourcable = new SourceBeforePrint(sourcable);
		sourcable = new SourceAfterPrint(sourcable);*/
		
		Sourcable sourcable = new SourceBeforePrint(new SourceAfterPrint(new Source()));
		//Sourcable sourcable = new SourceAfterPrint(new SourceBeforePrint(new Source()));
		sourcable.print();	
	}
}
