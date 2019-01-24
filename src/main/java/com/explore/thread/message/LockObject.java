package com.explore.thread.message;

public class LockObject {

	private String message = null;// 返回消息

	Object object = new Object();

	boolean signallde = false;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void doWait() {
		doWait(0);
	}

	public void doWait(long timeout) {
		synchronized (object) {
			while (!signallde) {
				try {
					System.out.println("-------------------0" + signallde);
					object.wait();
					System.out.println("-------------------1" + signallde);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			signallde = false;
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
