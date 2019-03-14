package pers.allen.explore.pattern.proxy;


import org.junit.Test;

import pers.allen.explore.pattern.proxy.static0.RobotImpl;
import pers.allen.explore.pattern.proxy.static0.RobotInterface;
import pers.allen.explore.pattern.proxy.static0.RobotProxy;

public class ProxyTest {
	
	@Test
	public void staticProxy() {
		new RobotProxy(new RobotImpl()).run();
	}	
	
	@Test
	public void dynamicProxy() throws Throwable {
		RobotInterface ri = new RobotImpl();   
		/*ProxyFactory pf = new ProxyFactory();
		pf.invoke(ri, ri.getClass().getMethod("run", null), null);*/
		
		/*ClassLoader cl = ri.getClass().getClassLoader();
		Class<?> [] ins = ri.getClass().getInterfaces();
		ProxyFactory pf = new ProxyFactory(ri);
		RobotInterface proxy = (RobotInterface) Proxy.newProxyInstance(cl, ins, pf);
		proxy.run();*/
	}
}
