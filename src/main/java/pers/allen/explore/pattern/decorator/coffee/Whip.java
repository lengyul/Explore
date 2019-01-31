package pers.allen.explore.pattern.decorator.coffee;

/**
 * 奶泡
 * @author lengyul
 *
 */
public class Whip extends CondimentDecorator {
	
	Beverage beverage;
	
	public Whip(Beverage beverage){
		this.beverage = beverage;
	}
	
	
	@Override
	public String getDescription() {
		
		return beverage.getDescription() + ",Whip";
	}

	@Override
	public double cost() {
		
		return beverage.cost() + 0.10;
	}

	

}
