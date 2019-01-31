package pers.allen.explore.pattern.factory.product.factory;

import pers.allen.explore.pattern.factory.product.ingredient.Cheese;
import pers.allen.explore.pattern.factory.product.ingredient.Clams;
import pers.allen.explore.pattern.factory.product.ingredient.Dough;
import pers.allen.explore.pattern.factory.product.ingredient.Pepperoni;
import pers.allen.explore.pattern.factory.product.ingredient.Sauce;
import pers.allen.explore.pattern.factory.product.ingredient.Veggies;
import pers.allen.explore.pattern.factory.product.ingredient.cheese.MozzarellaCheese;
import pers.allen.explore.pattern.factory.product.ingredient.clams.FrozenClams;
import pers.allen.explore.pattern.factory.product.ingredient.dough.ThickCrustDough;
import pers.allen.explore.pattern.factory.product.ingredient.sauce.PlumTomatoSauce;
import pers.allen.explore.pattern.factory.product.ingredient.veggies.Garlic;
import pers.allen.explore.pattern.factory.product.ingredient.veggies.Mushroom;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory{

	@Override
	public Dough createDough() {
		
		return new ThickCrustDough();
	}

	@Override
	public Sauce createSauce() {
		
		return new PlumTomatoSauce();
	}

	@Override
	public Cheese createCheese() {
		
		return new MozzarellaCheese();
	}

	@Override
	public Veggies[] createVeggies() {
		
		return new Veggies[]{new Garlic(),new Mushroom()};
	}

	@Override
	public Pepperoni createPepperoni() {
		
		return null;
	}

	@Override
	public Clams createClam() {
		
		return new FrozenClams();
	}

}
