package com.explore.thread.message;


public class Thread1 implements Runnable{
	
	private String data;
	private WaitNotifyObj object;
	
	public Thread1(String data,WaitNotifyObj object){
		this.data = data;
		this.object  = object;
	}
	@Override
	public void run() {
		//Thread1睡眠 5s
		try {
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName()+"执行完毕:"+data);
			object.setMessage(Thread.currentThread().getName()+"执行完毕");
			object.doNotifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
