package com.expolre.pattern.factory.pizza.store;

import com.expolre.pattern.factory.pizza.style.NYStyleCheesePizza;
import com.expolre.pattern.factory.pizza.style.NYStyleClamPizza;
import com.expolre.pattern.factory.pizza.style.Pizza;

/**
 * 纽约Pizza商店
 * @author lengyul
 *
 */
public class NYPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		if (type.equals("cheese")) {
			return new NYStyleCheesePizza();
		} else if(type.equals("clam")){
			return new NYStyleClamPizza();
		}
		return 	null;
	}

}
