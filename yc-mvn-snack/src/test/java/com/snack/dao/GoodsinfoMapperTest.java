package com.snack.dao;

import java.io.IOException;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.snack.bean.GoodsInfo;
import com.snack.bean.GoodsType;
import com.snack.bean.OrderInfo;
import com.snack.bean.OrderItemInfo;

public class GoodsinfoMapperTest {

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
	public void test2() throws IOException {
		GoodsType gt=new GoodsType();
		gt.setTname("测试分类");
		gt.setStatus(0);
		//session.insert("com.snack.dao.GoodsinfoMapper.insert",gt);
		
		GoodstypeMapper mapper=session.getMapper(GoodstypeMapper.class);
		mapper.insert(gt);
		//不commit,会话会在关闭前自动回滚
		session.commit();
		session.close();
	}
	
	
	@Test
	public void test3() throws IOException {
		
		GoodsType gt=new GoodsType();
		gt.getTno();
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
	public void test4() throws IOException {
		
		/*
		 * GoodsType gt=new GoodsType(); gt.setTno(66); gt.setTname("修改");
		 * gt.setStatus(1); session.delete("com.snack.dao.GoodsinfoMapper.delete",gt);
		 * //不commit,会话会在关闭前自动回滚 session.commit();
		 */
		
		GoodstypeMapper mapper=session.getMapper(GoodstypeMapper.class);
		mapper.delete(108);
		//不commit,会话会在关闭前自动回滚
		session.commit();
		session.close();
	}
	
	@Test
	public void test5() throws IOException {
		
		/*OrderitemMapper dom=session.getMapper(OrderitemMapper.class); 
		 * OrderitemMapper dom=session.getMapper(OrderitemMapper.class); GoodsinfoMapper
		 * dpm=session.getMapper(GoodsinfoMapper.class); OrderItemInfo
		 * doi=dom.selectById(6); GoodsInfo dp=dpm.selectById(doi.getGno());
		 */
		
		/**
		 * 测试驱动开发===》先写好所有的测试代码==》再业务代码
		 */
		OrderitemMapper dom=session.getMapper(OrderitemMapper.class); 
		OrderItemInfo doi=dom.selectById(16);
		
		//java 黑科技==》反射==》动态代理技术
		GoodsInfo dp=doi.getGoodsInfo();
		System.out.println(dp);
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
	
	
	@Test
	public void test7() throws IOException {
		GoodsinfoMapper mapper=session.getMapper(GoodsinfoMapper.class);
		System.out.println("=============");
		mapper.selectByObj(null);
		GoodsInfo gi=new GoodsInfo();
		System.out.println("=============");
		mapper.selectByObj(gi);
		
		gi.setGname("开心");
		System.out.println("===========================");
		mapper.selectByObj(gi);
		
		gi.setIntro("三只");
		System.out.println("===========================");
		mapper.selectByObj(gi);
		
	}
	
	@Test
	public void test8() throws IOException {
		GoodsinfoMapper mapper=session.getMapper(GoodsinfoMapper.class);
		int[] gnos= {1,2,3};
		mapper.selectByCids(gnos);
		
	}
	
	@Test
	public void test9() throws IOException {
		GoodsinfoMapper mapper=session.getMapper(GoodsinfoMapper.class);
		GoodsInfo gi=new GoodsInfo();
		//只修改一个价格字段值
		gi.setGno(1);
		gi.setPrice(45.5d);
		mapper.update(gi);
		//从数据库中查出该记录，验证结果
		GoodsInfo dbgi=mapper.selectById(1);
		
		Assert.assertEquals(45.5d, dbgi.getPrice(),0);
		//Assert.assertEquals(99, dbgi.getBalance(),0);
		Assert.assertEquals("开心果", dbgi.getGname());
	}
	
	
	@Test
	public void test10() throws IOException {
		OrderInfoMapper mapper=session.getMapper(OrderInfoMapper.class);
		OrderitemMapper oimapper=session.getMapper(OrderitemMapper.class);
		//生成订单业务
		OrderItemInfo oii=new OrderItemInfo();
		oii.setGno(2);
		oii.setNums(2);
		oii.setPrice(20);
		oii.setStatus(0);
		
		OrderInfo oi=new OrderInfo();
		oi.setAno("3");
		oi.setPrice(20);
		oi.setStatus(0);
		
		
		try {
			//写订单表
			mapper.insert(oi);
			oii.setOno(oi.getOno());
			oimapper.insert(oii);
			
			session.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			session.close();
		}finally {
			session.close();
		}
	}
}
