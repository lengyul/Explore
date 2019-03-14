package pers.allen.explore.pattern.proxy.static0;

public class RobotProxy implements RobotInterface{
	
	RobotInterface robotInterface;
	
	public RobotProxy(RobotInterface robotInterface) {
		this.robotInterface = robotInterface;
	}
	
	@Override
	public void run() {
		System.out.println("do something...");
		robotInterface.run();
	}

}
