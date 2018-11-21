package com.expolre.pattern.factory.product;
import com.expolre.pattern.factory.product.pizza.Pizza;

public abstract class PizzaStore {
	
	public Pizza orderPizza(String type){
		Pizza pizza = null;
		pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
	
	 abstract public Pizza createPizza(String type); 
}
