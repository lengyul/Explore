package pers.allen.explore.juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList - 写入并复制
 * 使用：添加操作多，效率低，每次添加时都会复制；并发迭代多可以选择使用。
 * @author lengyul
 *
 */
public class CopyOnWriteArrayListTest {
		
	public static void main(String[] args) throws InterruptedException {
		
		ListModifyDemo ldd =new ListModifyDemo();
		new Thread(ldd).start();
		
		Thread.sleep(500);
		new Thread(ldd).start();
		
	}
}

class ListModifyDemo implements Runnable{

	 private static List<String> list = new ArrayList<>();
     //private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
	
	static{
		list.add("A");
		list.add("B");
		list.add("C");
	}
	
	@Override
	public void run() {
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			//list.remove(0);
			list.add("D");
		}
		
	}
	
}