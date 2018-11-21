
package com.expolre.pattern.factory.car;

public class CarStore {
	
	SimpleCarFactory simpleCarFactory;
	
	public CarStore(SimpleCarFactory simpleCarFactory){
		this.simpleCarFactory = simpleCarFactory;
	}
	
	public Car create(String carType){
		
		Car car = simpleCarFactory.getCar(carType);
		System.out.println(car.getName()+".....");
		return car;
	}
}
