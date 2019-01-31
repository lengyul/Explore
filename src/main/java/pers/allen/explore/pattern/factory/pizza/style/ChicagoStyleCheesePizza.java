package pers.allen.explore.pattern.factory.pizza.style;

public class ChicagoStyleCheesePizza extends Pizza {
	public ChicagoStyleCheesePizza() {
		name = "Chicago Style Deep Dish Cheese Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";

		toppings.add("Shredded Mozzarella Cheese");
	}

	// 可以覆盖cut()方法
	public void cut() {
		System.out.println("Cutting the pizza into square slices");
	}

}
