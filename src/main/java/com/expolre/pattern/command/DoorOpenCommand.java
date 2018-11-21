package com.expolre.pattern.command;

public class DoorOpenCommand implements Command {

	private Door door;
	
	public DoorOpenCommand(Door door){
		this.door = door;
	}
	
	@Override
	public void execute() {
		door.open();
	}

}
