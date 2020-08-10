package com.yc.spring;

public class Hello {

	public Hello() {
		System.out.println("=======Hello 无参数的构造方法======================");
	}
	
	public Hello(int a) {
		System.out.println("=======Hello 有参数的构造方法======================");
	}
	
	public void sayHello() {
		System.out.println("hello world");
	}
	
	/**
	 * 生命周期方法不能带参数
	 */
	public void init() {
		System.out.println("=====hello 被创建=========");
	}
	
	public void destroy() {
		System.out.println("=====hello 被销毁=========");
	}
}
