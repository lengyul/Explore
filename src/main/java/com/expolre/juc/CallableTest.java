package com.expolre.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable接口：
 * 1.允许带返回值，并且可以抛出异常
 * 2.执行Callable，需要FutureTask类的支持用于接收结果
 * @author lengyul
 *
 */
public class CallableTest {
		
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableDemo cd =new CallableDemo();
		
		FutureTask<String> ft = new FutureTask<>(cd);
		new Thread(ft).start();
		
		System.out.println(ft.get());
	} 
	
}

class CallableDemo implements Callable<String>{

	@Override
	public String call() throws Exception {
		
		return "success";
	}
	
}