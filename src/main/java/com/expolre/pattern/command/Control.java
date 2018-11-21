package com.expolre.pattern.command;

public class Control {
	
	private Command cmd;;
	
	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}
	
	public void print(){
		cmd.execute();
	}
}
