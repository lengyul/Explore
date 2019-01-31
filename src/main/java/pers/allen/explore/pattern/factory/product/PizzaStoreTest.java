package pers.allen.explore.pattern.factory.product;

import org.junit.Test;

import pers.allen.explore.pattern.factory.product.pizza.Pizza;

public class PizzaStoreTest {
	
	@Test
	public void test(){
		
		/*PizzaStore pizzaStore = new NYPizzaStore();
		Pizza pizza = pizzaStore.orderPizza("cheese");
		System.out.println(pizza);*/
		
		PizzaStore pizzaStore = new ChicagoPizzaStore();
		Pizza pizza = pizzaStore.orderPizza("cheese");
		System.out.println(pizza);
	}
}
