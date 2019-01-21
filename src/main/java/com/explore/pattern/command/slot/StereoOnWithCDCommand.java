package com.explore.pattern.command.slot;

import com.explore.pattern.command.Command;

public class StereoOnWithCDCommand implements Command {

	Stereo stereo;
	
	public StereoOnWithCDCommand(Stereo stereo){
		this.stereo = stereo;
	}
	
	@Override
	public void execute() {
		stereo.on();
		stereo.setCD();
		stereo.setVolume();
	}

	@Override
	public void undo() {
		stereo.off();
	}

}
