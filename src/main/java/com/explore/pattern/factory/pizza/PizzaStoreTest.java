package com.explore.pattern.factory.pizza;

import org.junit.Test;

import com.explore.pattern.factory.pizza.store.ChicagoPizzaStore;
import com.explore.pattern.factory.pizza.store.NYPizzaStore;
import com.explore.pattern.factory.pizza.store.PizzaStore;
import com.explore.pattern.factory.pizza.style.Pizza;

public class PizzaStoreTest {

	@Test
	public void test(){
		
		PizzaStore pizzaStore = new ChicagoPizzaStore();
		/*PizzaStore pizzaStore = new NYPizzaStore();*/
		Pizza pizza = pizzaStore.orderPizza("cheese");
		System.out.println(pizza);
	}
	
}
