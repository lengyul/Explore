package pers.allen.explore.pattern.factory.product.factory;

import pers.allen.explore.pattern.factory.product.ingredient.Cheese;
import pers.allen.explore.pattern.factory.product.ingredient.Clams;
import pers.allen.explore.pattern.factory.product.ingredient.Dough;
import pers.allen.explore.pattern.factory.product.ingredient.Pepperoni;
import pers.allen.explore.pattern.factory.product.ingredient.Sauce;
import pers.allen.explore.pattern.factory.product.ingredient.Veggies;
import pers.allen.explore.pattern.factory.product.ingredient.cheese.ReggianoCheese;
import pers.allen.explore.pattern.factory.product.ingredient.clams.FreshClams;
import pers.allen.explore.pattern.factory.product.ingredient.dough.ThinCrustDough;
import pers.allen.explore.pattern.factory.product.ingredient.pepperoni.SlicedPepperoni;
import pers.allen.explore.pattern.factory.product.ingredient.sauce.MarinaraSauce;
import pers.allen.explore.pattern.factory.product.ingredient.veggies.Garlic;
import pers.allen.explore.pattern.factory.product.ingredient.veggies.Mushroom;
import pers.allen.explore.pattern.factory.product.ingredient.veggies.Onion;
import pers.allen.explore.pattern.factory.product.ingredient.veggies.RedPepper;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Dough createDough() {
		
		return new ThinCrustDough();
	}

	@Override
	public Sauce createSauce() {
		
		return new MarinaraSauce();
	}

	@Override
	public Cheese createCheese() {
		
		return new ReggianoCheese();
	}

	@Override
	public Veggies[] createVeggies() {
		
		return new Veggies[]{new Garlic(),new Onion(),new Mushroom(),new RedPepper()};
	}

	@Override
	public Pepperoni createPepperoni() {
		
		return new SlicedPepperoni();
	}

	@Override
	public Clams createClam() {
		
		return new FreshClams();
	}

	

}
