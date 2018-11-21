package com.expolre.juc;

/**
 * 生产者消费者模式(线程通讯)
 * @author lengyul
 *
 */
public class SyncProducerAndConsumerTest {
		
	public static void main(String[] args) {
		SyncCustomer customer = new SyncCustomer();
		
		SyncProducer producer = new SyncProducer(customer);
		SyncConsumer consumer = new SyncConsumer(customer);
		
		new Thread(producer,"生产者A").start();
		new Thread(consumer,"消费者A").start();
		
		/*new Thread(producer,"生产者B").start();
		new Thread(consumer,"消费者B").start();*/
	}
}
class SyncCustomer{
	private int product = 0;
	 /*
	  * As in the one argument version, interrupts and spurious wakeups are possible, 
	  * and this method should always be used in a loop
	  */
	public synchronized void increment() throws InterruptedException{
		while(product >= 1) { 
			System.out.println("产品数量已满");
			this.wait();
		}
		System.out.println(Thread.currentThread().getName()+"："+ ++product);
		this.notifyAll();
	}
	
	public synchronized void decreasing() throws InterruptedException{
		while(product <= 0) {
			System.out.println("产品已售空");
			this.wait();
		}
		System.out.println(Thread.currentThread().getName()+"："+ --product);
		this.notifyAll();
	}
}

class SyncProducer implements Runnable{

	private SyncCustomer customer;
	
	public SyncProducer(SyncCustomer customer){
		this.customer = customer;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(200);
				customer.increment();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SyncConsumer implements Runnable{
	
	private SyncCustomer customer;
	
	public SyncConsumer(SyncCustomer customer){
		this.customer = customer;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				customer.decreasing();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
