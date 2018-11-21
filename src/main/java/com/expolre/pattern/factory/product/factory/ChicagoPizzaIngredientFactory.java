package com.expolre.pattern.factory.product.factory;

import com.expolre.pattern.factory.product.ingredient.Cheese;
import com.expolre.pattern.factory.product.ingredient.Clams;
import com.expolre.pattern.factory.product.ingredient.Dough;
import com.expolre.pattern.factory.product.ingredient.Pepperoni;
import com.expolre.pattern.factory.product.ingredient.Sauce;
import com.expolre.pattern.factory.product.ingredient.Veggies;
import com.expolre.pattern.factory.product.ingredient.cheese.MozzarellaCheese;
import com.expolre.pattern.factory.product.ingredient.clams.FrozenClams;
import com.expolre.pattern.factory.product.ingredient.dough.ThickCrustDough;
import com.expolre.pattern.factory.product.ingredient.sauce.PlumTomatoSauce;
import com.expolre.pattern.factory.product.ingredient.veggies.Garlic;
import com.expolre.pattern.factory.product.ingredient.veggies.Mushroom;

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
