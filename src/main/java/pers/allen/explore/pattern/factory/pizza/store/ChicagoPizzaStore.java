package pers.allen.explore.pattern.factory.pizza.store;

import pers.allen.explore.pattern.factory.pizza.style.ChicagoStyleCheesePizza;
import pers.allen.explore.pattern.factory.pizza.style.ChicagoStyleClamPizza;
import pers.allen.explore.pattern.factory.pizza.style.NYStyleCheesePizza;
import pers.allen.explore.pattern.factory.pizza.style.NYStyleClamPizza;
import pers.allen.explore.pattern.factory.pizza.style.Pizza;

/**
 * 芝加哥Pizza商店
 * @author lengyul
 *
 */
public class ChicagoPizzaStore extends PizzaStore{

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if (type.equals("cheese")) {
			return new ChicagoStyleCheesePizza();
		} else if(type.equals("clam")){
			return new ChicagoStyleClamPizza();
		}
		return pizza;
	}

}
