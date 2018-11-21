package com.expolre.pattern.command;

public class CommandTest {
			
	public static void main(String[] args) {
		
		Control control = new Control();
		
		
		Command cmdLight = new LightOnCommand(new Light()); //命令开灯
		Command cmdDoor  = new DoorOpenCommand(new Door()); //命令开门
		
		control.setCmd(cmdDoor);
		control.print();
		
		control.setCmd(cmdLight);
		control.print();
	}
}
