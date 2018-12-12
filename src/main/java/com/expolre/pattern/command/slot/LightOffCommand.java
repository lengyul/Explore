package com.expolre.pattern.command.slot;

import com.expolre.pattern.command.Command;
import com.expolre.pattern.command.simple.Light;

public class LightOffCommand implements Command {

	Light light;
	
	public LightOffCommand(Light light){
		this.light = light;
	}
	
	@Override
	public void execute() {
		light.off();
	}

	@Override
	public void undo() {
		light.on();
	}

}
