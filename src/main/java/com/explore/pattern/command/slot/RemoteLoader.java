package com.explore.pattern.command.slot;

import com.explore.pattern.command.simple.Door;
import com.explore.pattern.command.simple.DoorOpenCommand;
import com.explore.pattern.command.simple.Light;
import com.explore.pattern.command.simple.LightOnCommand;

/**
 * 创建命令对象进行测试
 * @author lengyul
 *
 */
public class RemoteLoader {
	public static void main(String[] args) {
		//初始化控制器大小
		RemoteControl remoteControl = new RemoteControl(3);
		
		//创建装置对象
		Door door = new Door();
		Light light = new Light();
		Stereo stereo = new Stereo();
		//创建命令对象
		DoorOpenCommand doorOpenCommand = new DoorOpenCommand(door);
		DoorCloseCommand doorCloseCommand = new DoorCloseCommand(door);
		LightOnCommand lightOnCommand = new LightOnCommand(light);
		LightOffCommand lightOffCommand = new LightOffCommand(light);
		StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
		StereoOffCommand stereOffCommand = new StereoOffCommand(stereo);
		//将命令加载到控制器中
		remoteControl.setCommand(0, doorOpenCommand, doorCloseCommand);
		remoteControl.setCommand(1, lightOnCommand, lightOffCommand);
		remoteControl.setCommand(2, stereoOnWithCDCommand, stereOffCommand);
		System.out.println(remoteControl.toString());
		//启动命令按钮
		remoteControl.onButtonWasPushed(0);
		remoteControl.undoButtonWasPushed(0); //撤销命令
		remoteControl.offButtonWasPushed(0);
		remoteControl.undoButtonWasPushed(0); //撤销命令
		/*remoteControl.onButtonWasPushed(1);
		remoteControl.offButtonWasPushed(1);
		remoteControl.onButtonWasPushed(2);
		remoteControl.offButtonWasPushed(2);*/
	}
}
