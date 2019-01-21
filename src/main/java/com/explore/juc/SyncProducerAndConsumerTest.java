package com.explore.juc;

/**
 * 生产者消费者模式(线程通信)
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
	private int product = 1;
	 /*
	  * As in the one argument version, interrupts and spurious wakeups are possible, 
	  * and this method should always be used in a loop
	  */
	public synchronized void increment() throws InterruptedException{
		while (product >= 1) {
			System.out.println("当前产品数量为："+product+"，等待消费者进行消费");
			this.wait();
		}
		System.out.println("["+Thread.currentThread().getName()+"] "+"消费者消费成功，继续生产"+ ++product);
		this.notifyAll(); //生产成功再次唤醒消费者进行消费
	}
	
	public synchronized void decreasing() throws InterruptedException{
		while (product <= 0) {
			System.out.println("当前产品数量为："+product+"，等待生产者继续生产");
			this.wait();
		}
		System.out.println("["+Thread.currentThread().getName()+"] "+"生产者生产成功，继续消费"+ --product);
		this.notifyAll(); //消费者再次通知生产者进行生产
	}
	
}

class SyncProducer implements Runnable{

	private SyncCustomer customer;
	
	public SyncProducer(SyncCustomer customer){
		this.customer = customer;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				// Thread.sleep(200);
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
		for (int i = 0; i < 10; i++) {
			try {
				customer.decreasing();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
