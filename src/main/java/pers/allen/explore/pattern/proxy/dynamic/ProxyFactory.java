package pers.allen.explore.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFactory implements InvocationHandler{

	private Object object;
	
	public ProxyFactory() {} 
	public ProxyFactory(Object object) {
		this.object = object;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object result = method.invoke(proxy, args);
		System.out.println("Invoke end...");
		return result;
	}
	
	

}
