package pers.allen.explore.pattern.factory.product;
import pers.allen.explore.pattern.factory.product.pizza.Pizza;

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
