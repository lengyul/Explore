package com.explore.pattern.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IoAopServiceHandler implements InvocationHandler{

	private Object object;
	
	IoAopServiceHandler(Object object) {
		this.object = object;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object result = method.invoke(object, args);
		
		return result;
	}

}
