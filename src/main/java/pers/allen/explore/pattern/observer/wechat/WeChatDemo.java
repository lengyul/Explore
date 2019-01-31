
package pers.allen.explore.pattern.observer.wechat;

import org.junit.Test;

public class WeChatDemo {
	
	@Test
	public void test(){
		
		WeChatServer wcs =new WeChatServer();
		
		//创建观察者被添加到可观察者中
		WeChatUser wcu1 = new WeChatUser();
		WeChatUser wcu2 = new WeChatUser();
		WeChatUser wcu3 = new WeChatUser();
		wcs.addObserver(wcu1);
		wcs.addObserver(wcu2);
		wcs.addObserver(wcu3);
		//推送数据到所有用户
		wcs.setInformation("hello");
		System.out.println();
		//删除用户1，再次推送
		wcs.deleteObserver(wcu1);
		wcs.setInformation("world");
	}

}
