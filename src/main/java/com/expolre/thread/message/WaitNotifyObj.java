package com.expolre.thread.message;

public class WaitNotifyObj {
	
	private String message = null;//返回消息
	Object object = new Object();
	boolean signallde = false;
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void doWait() {
		synchronized (object) {
			while (!signallde) {
				try {
					object.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			signallde = false;
		}
	}
	
	public void doWait(long timeout) {
		synchronized (object) {
				try {
					object.wait(timeout);
					signallde = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	public void doNotify() {
		synchronized (object) {
			signallde = true;
			object.notify();
		}
	}

	public void doNotifyAll() {
		synchronized (object) {
			signallde = true;
			object.notifyAll();
		}
	}
	   
}
