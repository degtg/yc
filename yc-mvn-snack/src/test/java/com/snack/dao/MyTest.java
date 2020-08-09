package com.snack.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.snack.bean.GoodsInfo;
import com.snack.bean.GoodsType;
import com.snack.bean.OrderItemInfo;

public class MyTest {

	private SqlSession session;
	//动态块
	{
		try {
			//mybatis配置文件
			String resource = "mybatis.xml";
			//读入配置文件
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//构建会话工厂==》23设计工厂模式
			SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
			
			//开启会话
			session=sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
	@Test
	public void test1() throws IOException {
		
		
		List<GoodsInfo> list=session.selectList("com.snack.dao.GoodsinfoMapper.selectAll");
		
		/**
		 * 使用断言进行结果的判断
		 * 
		 * true 表示的期望值
		 * list.size()>0是实际值
		 */
		Assert.assertEquals(true, list.size()>0);
		
		/* for (GoodsInfo goodsInfo : list) { System.out.println(goodsInfo); } */
		 
	}
	
	
	
	@Test
	public void test3() throws IOException {
		
		GoodsType gt=new GoodsType();
		gt.setTno(111);
		gt.setTname("修改");
		gt.setStatus(1);
		//session.update("com.snack.dao.GoodsinfoMapper.update",gt);
		GoodstypeMapper mapper=session.getMapper(GoodstypeMapper.class);
		mapper.update(gt);
		//不commit,会话会在关闭前自动回滚
		session.commit();
		session.close();
	}
	
	@Test
	public void test6() throws IOException {
		OrderitemMapper dom=session.getMapper(OrderitemMapper.class); 
		OrderItemInfo doi=dom.selectById(16);
		
		List<GoodsInfo> list=doi.getGoodslist();
		for (GoodsInfo goodsInfo : list) {
			System.out.println(goodsInfo);
		}
		session.close();
	}
	
}
