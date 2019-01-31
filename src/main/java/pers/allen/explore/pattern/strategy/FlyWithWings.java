package pers.allen.explore.pattern.strategy;

public class FlyWithWings implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("I am flying");
	}

}
