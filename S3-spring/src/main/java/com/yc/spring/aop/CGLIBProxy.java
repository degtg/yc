package com.yc.spring.aop;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLIBProxy implements MethodInterceptor {
	public static void main(String[] args) {
		RealSubject rs =new RealSubject();
		Houseowner ho =new Houseowner();
		CGLIBProxy cp =new CGLIBProxy();
		RealSubject proxyRs = (RealSubject) cp.propxy(rs);
		proxyRs.say();
		Houseowner proxyHo =(Houseowner) cp.propxy(ho);
		proxyHo.sale();
	}
	
	private Object realSubject;
	public Object intercept(
			Object obj,
			Method method,
			Object[] args,
			MethodProxy methodProxy) throws Throwable {
		System.out.println("代理类"+obj.getClass());
		if (args !=null && args.length>0) {//方法的参数值
			for (int i = 0; i < args.length; i++) {
				System.out.println("方法参数"+args[i]);
			}
		}
		Object returnvalue = null; //方法返回值，无返回类型时，为null
		returnvalue = methodProxy.invoke(this.realSubject, args);//调用目标对象的方法
		System.out.println("方法的返回值"+returnvalue);
		return returnvalue;
	}
	
	/*
	 * 代理方法
	 */
	public Object propxy(Object targetObject) {
		this.realSubject=targetObject;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetObject.getClass());//设置父类
		enhancer.setCallback(this);//设置回调对象本身
		return enhancer.create();//创建代理对象
	}

}

	
	
