package com.yc.spring.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.yc.spring.Hello;
import com.yc.spring.bean.Person;

@Configuration
@ComponentScan("com.yc.spring")
/*
 * AOP不是Spring提供的概念
 * Spring 对AOP的依赖AspectJ框架
 */
@EnableAspectJAutoProxy
public class AOPConfig {
	
	@Bean
	public Person getPerson() {
		return new Person();
		
	}
	
	@Bean
	public Hello getHello() {
		return new Hello();
		
	}
}
