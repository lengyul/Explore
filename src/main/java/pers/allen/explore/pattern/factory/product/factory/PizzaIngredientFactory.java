package pers.allen.explore.pattern.factory.product.factory;

import pers.allen.explore.pattern.factory.product.ingredient.Cheese;
import pers.allen.explore.pattern.factory.product.ingredient.Clams;
import pers.allen.explore.pattern.factory.product.ingredient.Dough;
import pers.allen.explore.pattern.factory.product.ingredient.Pepperoni;
import pers.allen.explore.pattern.factory.product.ingredient.Sauce;
import pers.allen.explore.pattern.factory.product.ingredient.Veggies;

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
