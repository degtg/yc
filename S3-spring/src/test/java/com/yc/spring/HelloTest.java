package com.yc.spring;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.spring.bean.Person;
import com.yc.spring.dao.UserDao;



public class HelloTest {
	
	private ClassPathXmlApplicationContext ctx;
	
	@Before
	public void before() {
		ctx =new ClassPathXmlApplicationContext("beans.xml");
		
	}
	
	@After
	public void after() {
		ctx =new ClassPathXmlApplicationContext("beans.xml");
		
	}
	
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
	/**
	 * Spring 框架解决的问题
	 * Servlet
	 * 	UserBiz ubiz=new UserBiz();
	 * UserBiz ubiz=new SubUserBiz1();
	 * UserBiz ubiz=new SubUserBiz2();
	 * 1.new 用来创建对象 =》内存中就会占用存储对象的空间
	 * 	每次new都会创建一个新的对象==》从而导致内存消耗大
	 * 解决的方式： 使用对象池
	 * 通过    对象池.get对象      来获取对象
	 * 
	 * 2.耦合性问题
	 * 	对象可以任意在运行期间设置未指定的子类实现类   
	 * spring 框架解除了对象与实现类之间的绑定关系
	 * 
	 * 控制反转：
	 * 	对象创建由程序员决定
	 * spring框架对象的创建由容器决定
	 */
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
	
	
	@Test
	public void test2() {
		Person p1=(Person) ctx.getBean("p1");
		Assert.assertEquals("王默", p1.getName());
		Assert.assertEquals(12, p1.getAge());
		Assert.assertEquals(4, p1.getKilleds().size());
		Assert.assertEquals("wm", p1.getKilleds().get(2));
	}
}
