package com.yc.LxBlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yc.LxBlog.dao")
public class LxBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(LxBlogApplication.class, args);
	}

}
