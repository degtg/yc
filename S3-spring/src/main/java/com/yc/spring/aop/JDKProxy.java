package com.yc.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler{

	public static void main(String[] args) {
		JDKProxy jp=new JDKProxy();
		RealSubject rs=new RealSubject();
		//对应ProxySubject ps=new ProxySubject();
		Subject ps=(Subject) jp.createProxyInstance(rs);
		ps.say();
		Houseowner ho=new Houseowner();
		Broker bk=(Broker) jp.createProxyInstance(ho);
		bk.sale();
	}
	//被代理对象
	private Object realSubject;
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//前置增强
		System.out.println("我的当事人有不在场的证据");
		//执行目标方法
		method.invoke(realSubject, args);
		//后置增强
		System.out.println("我的当事人有不在场的证据");

		return null;
	}
	/**
	 * 构建代理对象
	 */
	public Object createProxyInstance(Object tagerObject) {
		this.realSubject=tagerObject;
		return Proxy.newProxyInstance(tagerObject.getClass().getClassLoader(),
				tagerObject.getClass().getInterfaces(), 
				this);
		
	}

}

interface Broker{
	void sale();
}

class Houseowner implements Broker{

	public void sale() {
		System.out.println("房子又大又宽敞");
	}
	
}