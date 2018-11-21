package com.expolre.pattern.factory.pizza.store;

import com.expolre.pattern.factory.pizza.style.Pizza;

/**
 * Pizza商店
 * @author lengyul
 *
 */
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
