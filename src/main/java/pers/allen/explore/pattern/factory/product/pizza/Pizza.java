package pers.allen.explore.pattern.factory.product.pizza;

import pers.allen.explore.pattern.factory.product.ingredient.Cheese;
import pers.allen.explore.pattern.factory.product.ingredient.Clams;
import pers.allen.explore.pattern.factory.product.ingredient.Dough;
import pers.allen.explore.pattern.factory.product.ingredient.Pepperoni;
import pers.allen.explore.pattern.factory.product.ingredient.Sauce;
import pers.allen.explore.pattern.factory.product.ingredient.Veggies;

public abstract class Pizza {
	String name;
	public Dough dough;
	Sauce sauce;
	Cheese cheese;
	Veggies [] veggies;
	Clams clams;
	Pepperoni peperoni;
	
	abstract public void prepare();
	
	public void bake() {
		System.out.println("将Pizza烘烤25分钟");
	}
	public void cut() {
		System.out.println("将Pizza对角切片");
	}
	public void box() {
		System.out.println("将Pizza装盒子");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
