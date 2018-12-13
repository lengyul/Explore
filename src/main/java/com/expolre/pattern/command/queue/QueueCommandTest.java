package com.expolre.pattern.command.queue;

public class QueueCommandTest {
	public static void main(String[] args) throws InterruptedException {
		QueueCommand queueCommand = new QueueCommand();

		new Thread(() ->{
			CommandHandler commandHandler = new CommandHandler(queueCommand,new CallCommand());
			commandHandler.handler();
		}).start();
		
		//添加命令到队列
		AccpetCommand accpetCommand = new AccpetCommand();
		ReadCommand readCommand = new ReadCommand();
		WriteCommand writeCommand = new WriteCommand();
		queueCommand.add(accpetCommand);
		queueCommand.add(readCommand);
		queueCommand.add(writeCommand);
		queueCommand.add(accpetCommand);
		queueCommand.add(readCommand);
		queueCommand.add(writeCommand);
		
	}
}
