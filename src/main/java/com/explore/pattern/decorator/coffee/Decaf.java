package com.explore.pattern.decorator.coffee;

/**
 * 低咖啡因
 * @author lengyul
 *
 */
public class Decaf extends Beverage {

	public Decaf(){
		description = "Decaf";
	}
	
	@Override
	public double cost() {

		return 1.05;
	}

}
