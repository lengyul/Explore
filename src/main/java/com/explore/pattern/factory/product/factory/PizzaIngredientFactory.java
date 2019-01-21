package com.explore.pattern.factory.product.factory;

import com.explore.pattern.factory.product.ingredient.Cheese;
import com.explore.pattern.factory.product.ingredient.Clams;
import com.explore.pattern.factory.product.ingredient.Dough;
import com.explore.pattern.factory.product.ingredient.Pepperoni;
import com.explore.pattern.factory.product.ingredient.Sauce;
import com.explore.pattern.factory.product.ingredient.Veggies;

/**
 * 创建Pizza材料工厂
 * @author lengyul
 *
 */
public interface PizzaIngredientFactory {
	
	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();
}	
