package com.expolre.pattern.factory.pizza;

import org.junit.Test;

import com.expolre.pattern.factory.pizza.store.ChicagoPizzaStore;
import com.expolre.pattern.factory.pizza.store.NYPizzaStore;
import com.expolre.pattern.factory.pizza.store.PizzaStore;
import com.expolre.pattern.factory.pizza.style.Pizza;

public class PizzaStoreTest {

	@Test
	public void test(){
		
		PizzaStore pizzaStore = new ChicagoPizzaStore();
		/*PizzaStore pizzaStore = new NYPizzaStore();*/
		Pizza pizza = pizzaStore.orderPizza("cheese");
		System.out.println(pizza);
	}
	
}
