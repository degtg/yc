package com.yc.spring.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.yc.spring")
/*
 * AOP不是Spring提供的概念
 * Spring 对AOP的依赖AspectJ框架
 */
@EnableAspectJAutoProxy
public class AOPConfig {
	

}
