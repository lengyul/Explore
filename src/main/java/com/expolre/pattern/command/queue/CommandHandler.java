package com.expolre.pattern.command.queue;

import java.util.List;
import com.expolre.pattern.command.Command;

/**
 * 命令处理器
 * @author lengyul
 * @date 2018年12月13日 上午11:10:53
 */
public class CommandHandler {
	
	CallCommand callCommand;
	QueueCommand queueCommand;
	public CommandHandler(QueueCommand queueCommand,CallCommand callCommand){
		this.queueCommand = queueCommand;
		this.callCommand = callCommand;
	}
	
	public void handler(){
		while (true) {
			List<Command> commands = queueCommand.getCommands(); //获取请求的command，阻塞直到返回command
			System.out.println(commands.size());
			for (Command command : commands) {				
				callCommand.setCommand(command);			
				callCommand.run(); //执行命令
			}
		}
	}
	
}
