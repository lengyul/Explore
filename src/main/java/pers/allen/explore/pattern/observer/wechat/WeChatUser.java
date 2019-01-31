package pers.allen.explore.pattern.observer.wechat;

import java.util.Observable;
import java.util.Observer;

public class WeChatUser implements Observer,MessageDisplay {
	
	private String message;
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof WeChatServer) {
			WeChatServer user = (WeChatServer)o;
			this.message = user.getMessage();
		}
		print();
	}

	@Override
	public void print() {
		System.out.println(this+"接收到消息："+message);
	}

}
