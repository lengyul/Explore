package com.explore.pattern.decorator.coffee;

/**
 * 混合咖啡
 * @author lengyul
 *
 */
public class HouseBlend extends Beverage {
	
	public HouseBlend(){
		description = "HouseBlend";
	}

	@Override
	public double cost() {
		
		return 0.89;
	}
	
	
}
