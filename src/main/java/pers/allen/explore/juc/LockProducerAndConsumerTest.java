package pers.allen.explore.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式(线程通讯)
 * @author lengyul
 *
 */
public class LockProducerAndConsumerTest {
		
	public static void main(String[] args) {
		LockCustomer customer =new LockCustomer();
		
		LockProducer producer =new LockProducer(customer);
		LockConsumer consumer =new LockConsumer(customer);
		
		new Thread(producer,"生产者A").start();
		new Thread(consumer,"消费者A").start();
		
		new Thread(producer,"生产者B").start();
		new Thread(consumer,"消费者B").start();
	}
}
class LockCustomer{
	private int product = 0;
	
	private Lock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	public void increment() throws InterruptedException{
		try {
			lock.lock();
			while(product >= 1) { 
				System.out.println("产品数量已满");
				condition.await();
			}
			System.out.println(Thread.currentThread().getName()+"："+ ++product);
			condition.signalAll();
		}finally{
			lock.unlock();
		}
		
	}
	
	public void decreasing() throws InterruptedException{
		try {
			lock.lock();
			while(product <= 0) {
				System.out.println("产品已售空");
				condition.await();
			}
			System.out.println(Thread.currentThread().getName()+"："+ --product);
			condition.signalAll();
		}finally{
			lock.unlock();
		}
		
	}
}

class LockProducer implements Runnable{

	private LockCustomer customer;
	
	public LockProducer(LockCustomer customer){
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

class LockConsumer implements Runnable{
	
	private LockCustomer customer;
	
	public LockConsumer(LockCustomer customer){
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
