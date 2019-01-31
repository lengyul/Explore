package pers.allen.explore.pattern.factory.product;

import pers.allen.explore.pattern.factory.product.factory.NYPizzaIngredientFactory;
import pers.allen.explore.pattern.factory.product.factory.PizzaIngredientFactory;
import pers.allen.explore.pattern.factory.product.pizza.CheesePizza;
import pers.allen.explore.pattern.factory.product.pizza.ClamsPizza;
import pers.allen.explore.pattern.factory.product.pizza.Pizza;

public class NYPizzaStore extends PizzaStore{

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory  = new NYPizzaIngredientFactory();
		
		if (type.equals("cheese")) {
			 pizza = new CheesePizza(ingredientFactory);
			 pizza.setName("New York Style Cheese Pizza");
		} else if(type.equals("clams")){
			pizza  = new ClamsPizza(ingredientFactory);
			pizza.setName("New York Style Clams Pizza");
		}
		
		return pizza;
	}



}
