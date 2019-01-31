package pers.allen.explore.pattern.command.simple;

import pers.allen.explore.pattern.command.Command;

public class DoorOpenCommand implements Command {

	private Door door;
	
	public DoorOpenCommand(Door door){
		this.door = door;
	}
	
	@Override
	public void execute() {
		door.open();
	}

	@Override
	public void undo() {
		door.close();
	}

}
