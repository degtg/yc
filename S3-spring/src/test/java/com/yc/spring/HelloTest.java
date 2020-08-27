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
	
	@Test
	public void test3() {
		Person p1=(Person) ctx.getBean(Person.class);
		Assert.assertEquals("likui", p1.getName());
		Assert.assertEquals(30, p1.getAge());
		Assert.assertEquals(null, p1.getKilleds());
	}
	
	@Test
	public void test4() {
		Person p1=(Person) ctx.getBean("p2");
		Assert.assertEquals("wuyong", p1.getName());
		Assert.assertEquals(38, p1.getAge());
		Assert.assertEquals("huarong", p1.getFriend().getName());
	}
	
	@Test
	public void test5() {
		Person p1=(Person) ctx.getBean("p4");
		Assert.assertEquals("wangying", p1.getName());
		Assert.assertEquals(40, p1.getAge());
	}
	
	@Test
	public void test6() {
		Person p1=(Person) ctx.getBean("p5");
		Assert.assertEquals("husanniang", p1.getName());
		Assert.assertEquals(20, p1.getAge());
	}
	
	/**
	 * bean的作用域
	 * 默认情况下，bean的作用域是单例模式
	 */
	@Test
	public void test7() {
		System.out.println("==========test7===========");
		
		Hello h=(Hello) ctx.getBean("hello");
		Hello h0_1=(Hello) ctx.getBean("hello");
		Hello h0_2=(Hello) ctx.getBean("hello");
		
		Hello h1=(Hello) ctx.getBean("hello1");
		Hello h1_1=(Hello) ctx.getBean("hello1");
		Hello h1_2=(Hello) ctx.getBean("hello1");
		
		
		System.out.println(h=h1);//false
		System.out.println(h0_1=h0_2);//true
		System.out.println(h1_1=h1_2);//false
		System.out.println(h1=h1_1);//false
		System.out.println(h1=h1_2);//false
		
	}
	
	
	@Test
	public void test8() {
		System.out.println("==========test8===========");
		
		Hello h=(Hello) ctx.getBean("hello2");
		h.sayHello();
		
	}
	
	/**
	 * 生命周期方法
	 */
	@Test
	public void test9() {
		System.out.println("==========test9===========");
		
		Hello h=(Hello) ctx.getBean("hello3");
		h.sayHello();
		
	}
	
	/**
	 * 自动装载
	 */
	@Test
	public void test10() {
		System.out.println("==========test10===========");
		
		Person p7=(Person) ctx.getBean("p7");
		System.out.println(p7.getFriend());
		
	}
}
