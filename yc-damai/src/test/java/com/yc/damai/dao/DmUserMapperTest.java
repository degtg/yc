package com.yc.damai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.yc.damai.po.DmUser;


public class DmUserMapperTest {
	private SqlSession session ;
	//动态块 
	{
		try {
			//mybatis 配置文件
			String resource="mybatis.xml";
			//读入配置文件
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//构建会话工厂 ==> 23 设计模式工厂模式
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//开启会话
			session = sqlSessionFactory.openSession();
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void test1() {
		DmUserMapper user =session.getMapper(DmUserMapper.class);
		DmUser du = new DmUser();
		du.setEname("w");
		du.setPassword("a");
		List<DmUser> list =user.findByTrem(du);
		System.out.println(list);
		
	}

}
