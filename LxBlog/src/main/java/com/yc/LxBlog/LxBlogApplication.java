package com.yc.LxBlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yc.LxBlog.web.LoginInterceptor;

@SpringBootApplication
@MapperScan("com.yc.LxBlog.dao")
public class LxBlogApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(LxBlogApplication.class, args);
	}

	/***
	 * 拦截器注册方法
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		InterceptorRegistration ir=registry.addInterceptor(new LoginInterceptor());
		//互联网项目==》拦截少数资源
		//企业内部管理系统==》拦截多数资源
		ir.addPathPatterns("/toAddArticle","/addArticle.do");
	}
	
	

}
