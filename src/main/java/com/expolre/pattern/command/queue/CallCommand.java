package com.expolre.pattern.command.queue;

import com.expolre.pattern.command.Command;

/**
 * 命令执行器
 * @author lengyul
 * @date 2018年12月13日 上午11:07:17
 */
public class CallCommand {
	
	Command command;//命令对象
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public void run(){
		if (command != null) {			
			command.execute();
		}else{
			System.out.println("---------"+command+"--------");
		}
	}
	
}
