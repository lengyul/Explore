package pers.allen.explore.thread.async;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 模拟异步操作，基于回调事件
 * 异步操作读取一个文件，当文件读取完毕时通知注册的回调类
 * @author lengyul
 * @date 2018年12月6日 下午4:03:08
 */
public class AsyncReadFileTest {
	
	public static void main(String[] args) throws Exception{
		String path = "C:\\Users\\Administrator\\Desktop\\工具.txt";
		
		FutureRead fr = new FutureRead(path,new ReadResultHandler());
		FutureTask<Integer> ft = new FutureTask<>(fr);
		new Thread(ft).start();
		System.out.println(ft.get()); //获取结果(阻塞线程直到操作完成并返回)
		
		// 线程池执行
		/*ExecutorService es = Executors.newFixedThreadPool(5);
		Future fu = es.submit(fr);
		System.out.println(fu.get());
		*/
		
		
		System.out.println("------------继续执行-----------------");
		CountDownLatch cdt = new CountDownLatch(1);
		cdt.await(); //防止主线程停止运行
	}
	
	
	
}
