package com.expolre.pattern.factory.product.factory;

import com.expolre.pattern.factory.product.ingredient.Cheese;
import com.expolre.pattern.factory.product.ingredient.Clams;
import com.expolre.pattern.factory.product.ingredient.Dough;
import com.expolre.pattern.factory.product.ingredient.Pepperoni;
import com.expolre.pattern.factory.product.ingredient.Sauce;
import com.expolre.pattern.factory.product.ingredient.Veggies;

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
