package com.explore.pattern.command.slot;

import com.explore.pattern.command.Command;
import com.explore.pattern.command.simple.Door;

public class DoorCloseCommand implements Command{

	Door door;
	
	public DoorCloseCommand(Door door){
		this.door = door;
	}
	
	@Override
	public void execute() {
		door.close();
	}

	@Override
	public void undo() {
		door.open();
	}

}
