package com.expolre.pattern.factory.product.factory;

import com.expolre.pattern.factory.product.ingredient.Cheese;
import com.expolre.pattern.factory.product.ingredient.Clams;
import com.expolre.pattern.factory.product.ingredient.Dough;
import com.expolre.pattern.factory.product.ingredient.Pepperoni;
import com.expolre.pattern.factory.product.ingredient.Sauce;
import com.expolre.pattern.factory.product.ingredient.Veggies;
import com.expolre.pattern.factory.product.ingredient.cheese.ReggianoCheese;
import com.expolre.pattern.factory.product.ingredient.clams.FreshClams;
import com.expolre.pattern.factory.product.ingredient.dough.ThinCrustDough;
import com.expolre.pattern.factory.product.ingredient.pepperoni.SlicedPepperoni;
import com.expolre.pattern.factory.product.ingredient.sauce.MarinaraSauce;
import com.expolre.pattern.factory.product.ingredient.veggies.Garlic;
import com.expolre.pattern.factory.product.ingredient.veggies.Mushroom;
import com.expolre.pattern.factory.product.ingredient.veggies.Onion;
import com.expolre.pattern.factory.product.ingredient.veggies.RedPepper;

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
