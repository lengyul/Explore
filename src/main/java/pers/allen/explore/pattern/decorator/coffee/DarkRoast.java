package pers.allen.explore.pattern.decorator.coffee;

/**
 * 烘焙度咖啡
 * @author lengyul
 *
 */
public class DarkRoast extends Beverage {
	
	public DarkRoast(){
		description = "DarkRoast";
	}
	
	@Override
	public double cost() {
	
		return 0.99;
	}

}
