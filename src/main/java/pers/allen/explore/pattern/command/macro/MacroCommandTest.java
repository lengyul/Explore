package pers.allen.explore.pattern.command.macro;

import pers.allen.explore.pattern.command.Command;
import pers.allen.explore.pattern.command.simple.Door;
import pers.allen.explore.pattern.command.simple.DoorOpenCommand;
import pers.allen.explore.pattern.command.simple.Light;
import pers.allen.explore.pattern.command.simple.LightOnCommand;
import pers.allen.explore.pattern.command.slot.DoorCloseCommand;
import pers.allen.explore.pattern.command.slot.LightOffCommand;
import pers.allen.explore.pattern.command.slot.RemoteControl;
import pers.allen.explore.pattern.command.slot.Stereo;
import pers.allen.explore.pattern.command.slot.StereoOffCommand;
import pers.allen.explore.pattern.command.slot.StereoOnWithCDCommand;

public class MacroCommandTest {
	public static void main(String[] args) {
		RemoteControl remoteControl = new RemoteControl(1);
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
		//封装宏命令
		Command[] partyOn = {doorOpenCommand,lightOnCommand,stereoOnWithCDCommand};
		Command[] partyOff = {doorCloseCommand,lightOffCommand,stereOffCommand};
		MacroCommand partyOnMacro = new MacroCommand(partyOn);
		MacroCommand partyOffMacro = new MacroCommand(partyOff);
		//将宏命令加载到控制器中
		remoteControl.setCommand(0, partyOnMacro, partyOffMacro);
		System.out.println(remoteControl.toString());
		//执行宏命令
		remoteControl.onButtonWasPushed(0);
		remoteControl.offButtonWasPushed(0);
	}
}
