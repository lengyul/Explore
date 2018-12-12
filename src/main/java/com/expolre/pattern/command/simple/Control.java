package com.expolre.pattern.command.simple;

import com.expolre.pattern.command.Command;

public class Control {
	
	private Command cmd;;
	
	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}
	
	public void print(){
		cmd.execute();
	}
}
