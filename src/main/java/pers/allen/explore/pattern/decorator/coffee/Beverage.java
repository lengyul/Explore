package pers.allen.explore.pattern.decorator.coffee;
/**
 * 饮品集合
 * @author lengyul
 *
 */
public abstract class Beverage {

	protected String description = null;

	public String getDescription() {
		return description;
	}
	
	public abstract double cost();
	
}
