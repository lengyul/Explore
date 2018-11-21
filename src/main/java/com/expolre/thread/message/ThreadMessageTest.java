package com.expolre.thread.message;
import java.time.LocalDateTime;


public class ThreadMessageTest {
		
	
	public static void main(String[] args) throws InterruptedException {
		WaitNotifyObj obj =new WaitNotifyObj();
		System.out.println(LocalDateTime.now());
		Thread t1 =new Thread(new Thread1("任务1",obj));
		Thread t2 =new Thread(new Thread2("任务2",obj));
		
		t1.start();
		t2.start();
		
		obj.doWait(1000 * 60 * 1); //等待一分钟 
		System.out.println(LocalDateTime.now()+":"+obj.getMessage());
		
	}
	
}
