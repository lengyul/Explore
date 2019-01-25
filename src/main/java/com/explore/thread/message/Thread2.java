package com.explore.thread.message;


public class Thread2 implements Runnable{
	
	private String data;
	private LockObject object;
	
	public Thread2(String data,LockObject object){
		this.data = data;
		this.object  = object;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName()+"执行完毕:"+data);
			object.setMessage(Thread.currentThread().getName()+"执行完毕");
			object.doNotifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}