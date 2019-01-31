package pers.allen.explore.pattern.factory.pizza;

import org.junit.Test;

import pers.allen.explore.pattern.factory.pizza.store.ChicagoPizzaStore;
import pers.allen.explore.pattern.factory.pizza.store.NYPizzaStore;
import pers.allen.explore.pattern.factory.pizza.store.PizzaStore;
import pers.allen.explore.pattern.factory.pizza.style.Pizza;

public class PizzaStoreTest {

	@Test
	public void test(){
		
		PizzaStore pizzaStore = new ChicagoPizzaStore();
		/*PizzaStore pizzaStore = new NYPizzaStore();*/
		Pizza pizza = pizzaStore.orderPizza("cheese");
		System.out.println(pizza);
	}
	
}
