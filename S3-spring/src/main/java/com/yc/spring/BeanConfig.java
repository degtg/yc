package com.yc.spring;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.yc.spring.bean.Person;
import com.yc.spring.dao.MySQLUserDao;
import com.yc.spring.dao.OracleUserDao;

/**
 * 注解方式配置IOC容器
 */
@Configuration //Ioc容器配置类的注解==》beans.xml
@ComponentScan("com.yc.spring")
public class BeanConfig {

	//xml中的每一个bean对应java的一个方法，这个方法返回bean对象
	//方法名没有限定
	@Bean(name="hello")
	public Hello getHello() {
		return new Hello();
		
	}
	
	
	
	/*
	 * @Bean(name="odao") public OracleUserDao getOracleUserDao() { return new
	 * OracleUserDao();
	 * 
	 * }
	 * 
	 * @Bean(name="mdao") public MySQLUserDao getMySQLUserDao() { return new
	 * MySQLUserDao();
	 * 
	 * }
	 */
	
	
	@Bean(name="p1")
	public Person getPerson1() {
		Person ret=new Person();
		ret.setName("王默");
		ret.setAge(12);
		ret.setKilleds(new ArrayList<String>());
		ret.getKilleds().add("de");
		ret.getKilleds().add("de");
		ret.getKilleds().add("wm");
		ret.getKilleds().add("de");
		
		return ret;
		
	}
	
	@Bean(name="p5")
	public Person getPerson5() {
		Person p=Person.PersonFactory();
		p.setName("husanniang");
		return p;
		
	}
	
	/**
	 * 原型模式
	 */
	@Bean("hello1")
	@Scope(value="prototype")
	public Hello getHello1() {
		return new Hello();
		
	}
	
	/**
	 * 懒加载
	 */
	@Bean("hello2")
	@Lazy
	public Hello getHello2() {
		return new Hello();
		
	}
	
}
