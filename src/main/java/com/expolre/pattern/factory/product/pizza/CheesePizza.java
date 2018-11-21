package com.expolre.pattern.factory.product.pizza;

import com.expolre.pattern.factory.product.factory.PizzaIngredientFactory;

public class CheesePizza extends Pizza{

	PizzaIngredientFactory ingredientFactory;
	
	public CheesePizza(PizzaIngredientFactory ingredientFactory){
		this.ingredientFactory = ingredientFactory;
	}
	
	@Override
	public void prepare() {
		 System.out.println("Preparing " + name);
		 dough = ingredientFactory.createDough();
		 sauce = ingredientFactory.createSauce();
		 cheese = ingredientFactory.createCheese();
	}

}
