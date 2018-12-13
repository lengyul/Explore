package com.expolre.pattern.command.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.expolre.pattern.command.Command;

public class QueueCommand {
	
	BlockingQueue<Command> commandQueue = new LinkedBlockingDeque<>();
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public QueueCommand() {
	}
	/**
	 * 获取队列中所有的请求Command对象
	 * @return
	 */
	public List<Command> getCommands() {
		if (commandQueue.isEmpty()) {
			try {
				lock.lock();
				if (commandQueue.isEmpty()) {
					System.out.println("------------------------await()");
					condition.await();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		List<Command> commands = new ArrayList<>();
		commandQueue.drainTo(commands); //获取并删除队列中所有的元素到集合中
		return commands;
	}
	
	public void add(Command command) throws InterruptedException{
		try {
			lock.lock();
			commandQueue.put(command);//put方法在队列size = capacity会阻塞，直到队列size < capacity
			condition.signalAll(); //如果put阻塞后，await会一直等待，导致无限阻塞
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		
		/*boolean result = commandQueue.offer(command);
		if (result) {
			try {
				lock.lock();
				condition.signalAll();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				lock.unlock();
			}
		} else {
			// add(command); //队列capacity数量限制测试，可能出现StackOverflowError异常
		}*/
	}
	
}
