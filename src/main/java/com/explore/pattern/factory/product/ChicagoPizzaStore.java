package com.explore.pattern.factory.product;

import com.explore.pattern.factory.product.factory.ChicagoPizzaIngredientFactory;
import com.explore.pattern.factory.product.factory.PizzaIngredientFactory;
import com.explore.pattern.factory.product.pizza.CheesePizza;
import com.explore.pattern.factory.product.pizza.ClamsPizza;
import com.explore.pattern.factory.product.pizza.Pizza;

public class ChicagoPizzaStore extends PizzaStore{
	
	public static void main(String[] args) {
		System.out.println(1 << 4);
	}
	
	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory  = new ChicagoPizzaIngredientFactory();
		
		if (type.equals("cheese")) {
			 pizza = new CheesePizza(ingredientFactory);
			 pizza.setName("Chicago York Style Cheese Pizza");
		} else if(type.equals("clams")){
			pizza  = new ClamsPizza(ingredientFactory);
			pizza.setName("Chicago York Style Clams Pizza");
		}
			
		return pizza;
	}
	
	

}
