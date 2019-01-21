package com.explore.pattern.factory.product.pizza;

import com.explore.pattern.factory.product.factory.PizzaIngredientFactory;

public class ClamsPizza extends Pizza{

	PizzaIngredientFactory ingredientFactory;
	
	public ClamsPizza(PizzaIngredientFactory ingredientFactory){
		this.ingredientFactory = ingredientFactory;
	}
	
	@Override
	public void prepare() {
		 System.out.println("Preparing " + name);
		 dough = ingredientFactory.createDough();
		 sauce = ingredientFactory.createSauce();
		 clams = ingredientFactory.createClam();
	}

}
