package com.expolre.pattern.command.slot;

import com.expolre.pattern.command.Command;
import com.expolre.pattern.command.NoCommand;

/**
 * 管理一组命令对象，每个按钮都有一个对应的命令对象
 * @author lengyul
 *
 */
public class RemoteControl {
	
	Command[] onCommands;
	Command[] offCommands;
	Command undoCommand; //记录前一个命令
	int capacity = 0;
	public RemoteControl(int capacity){
		this.capacity = capacity;
		onCommands = new Command[capacity];
		offCommands =new Command[capacity];
		//默认初始化为NoCommand,避免NullPointerExcetion
		Command noCommand = new NoCommand(); //defalut nocommand
		for (int i = 0; i < capacity; i++) {
			onCommands[i] = noCommand;
			offCommands[i]= noCommand;
		}
	}
	
	/**
	 * 保存指定位置的开关命令
	 * @param solt
	 * @param onCommand
	 * @param offCommand
	 */
	public void setCommand(int slot,Command onCommand,Command offCommand){
		onCommands[slot]  = onCommand;
		offCommands[slot] = offCommand;
	}
	
	/**
	 * 打开指定位置的命令
	 * @param slot
	 */
	public void onButtonWasPushed(int slot){
		onCommands[slot].execute();
		undoCommand = onCommands[slot];
	}
	
	/**
	 * 关闭指定位置的命令
	 * @param slot
	 */
	public void offButtonWasPushed(int slot){
		offCommands[slot].execute();
		undoCommand = offCommands[slot];
	}
	
	public void undoButtonWasPushed(int slot){
		undoCommand.undo(); //当前命令对象的倒转命令
	}
	/**
	 * 打印控制器所有的命令
	 */
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("-----------Remote Control----------\n");
		for (int i = 0; i < capacity; i++) {
			stringBuffer.append("[slot "+i+"]"+ onCommands[i].getClass().getName()
			+ " " + offCommands[i].getClass().getName()+"\n");
		}
		return stringBuffer.toString();
	}
	
}
