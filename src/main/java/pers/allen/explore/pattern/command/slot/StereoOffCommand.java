package pers.allen.explore.pattern.command.slot;

import pers.allen.explore.pattern.command.Command;

public class StereoOffCommand implements Command{

	Stereo stereo;
	
	public StereoOffCommand(Stereo stereo){
		this.stereo = stereo;
	}
	
	@Override
	public void execute() {
		stereo.off();
	}

	@Override
	public void undo() {
		stereo.on();
		stereo.setCD();
		stereo.setVolume();
	}
	
}
