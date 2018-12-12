package com.expolre.pattern.command.slot;

import com.expolre.pattern.command.Command;
import com.expolre.pattern.command.simple.Door;

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
