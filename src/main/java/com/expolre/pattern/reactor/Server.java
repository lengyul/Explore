package com.expolre.pattern.reactor;

public class Server {
	
	
	public static void main(String[] args) {
		Selector selector = new Selector();
		Acceptor acceptor = new Acceptor(8888, selector);

		
		//acceptor.addNewConnection(new InputSource("ceshi")); //模拟外部请求
		new Thread(acceptor).start();

		//初始化事件对应处理器
		Dispatcher dispatcher = new Dispatcher(selector);
		dispatcher.registerEventHandler(EventType.ACCPET,new AccpetEventHandler());
		dispatcher.handleEvents(); //从selector中获取事件，并转发到对应的handler中
	}
}
