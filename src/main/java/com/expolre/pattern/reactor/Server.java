package com.expolre.pattern.reactor;

/**
 * Reactor模式
 * @author lengyul
 * @date 2018年12月29日 下午2:51:53
 */
public class Server {
	
	
	public static void main(String[] args) {
		Selector selector = new Selector();
		Accpetor acceptor = new Accpetor(8888, selector);		
		new Thread(acceptor).start();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {			
				acceptor.addNewConnection(new InputSource("test")); //模拟外部请求
			}).start();
		}
		/*acceptor.addNewConnection(new InputSource("ceshi")); //模拟外部请求
		acceptor.addNewConnection(new InputSource("ceshi")); //模拟外部请求
*/		//初始化事件对应处理器
		Dispatcher dispatcher = new Dispatcher(selector);
		dispatcher.registerEventHandler(EventType.ACCPET,new AccpetEventHandler());
		dispatcher.handleEvents(); //从selector中获取事件，并转发到对应的handler中
	}
}
