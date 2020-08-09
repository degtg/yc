package com.yc.spring;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.spring.dao.UserDao;

public class HelloTest {
	
	@Test
	public void test() {
		//从Spring框架（容器中）中获取一个hello对象
		//创建spring容器对象
		ClassPathXmlApplicationContext ctx =
				new ClassPathXmlApplicationContext("beans.xml");
		
		Hello h=(Hello) ctx.getBean("hello");
		//执行sayHello方法
		h.sayHello();
		ctx.close();
	}

	@Test
	public void test1() {
		//从Spring框架（容器中）中获取一个hello对象
		//创建spring容器对象
		ClassPathXmlApplicationContext ctx =
				new ClassPathXmlApplicationContext("beans.xml");
		
		UserDao udao=(UserDao) ctx.getBean("mdao");
		UserDao odao=(UserDao) ctx.getBean("odao");
		udao.selectUserId("sisi");
		odao.selectUserId("sisi");
		ctx.close();
	}
}
