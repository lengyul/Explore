package com.explore.pattern.command.simple;

import com.explore.pattern.command.Command;

public class Control {
	
	private Command cmd;;
	
	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}
	
	public void print(){
		cmd.execute();
	}
}
