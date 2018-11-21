package com.expolre.pattern.factory.car;

/**
 * 创建一个工厂，生成基于给定信息的实体类的对象
 * @author lengyul
 *
 */
public class SimpleCarFactory {

	public Car getCar(String carType) {
		
		if (carType.equals("Audi")) {
			return new Audi();
		} else if (carType.equals("Benz")) {
			return new Benz();
		} else if (carType.equals("BMW")) {
			return new BMW();
		}
		return null;
		
	}
	
	
}
