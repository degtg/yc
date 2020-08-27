package com.yc.damai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.yc.damai.po.DmCategory;
import com.yc.damai.po.DmOrderitem;
import com.yc.damai.po.DmOrders;
import com.yc.damai.po.DmProduct;





public class MapperTest {
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
	
	//测试方法必须加 test 注解
	@Test
	public void test1() throws IOException {
		//session.selectList("xml接口的命名空间+英文点号+查询的sql的id");
		List<DmProduct> list = session.selectList("com.yc.damai.dao.DmProductMapper.selectAll");
		/**
		 * 使用断言进行结果的判断
		 * true 表示的期望值
		 * list.size()>0是实际值
		 */
		Assert.assertEquals(true, list.size()>0);
		
		  for(DmProduct dp : list) { System.out.println(dp); }
		 
	}
	
	@Test
	public void test2() throws IOException {
		DmCategory dc = new DmCategory();
		dc.setCname("测试分类");
		dc.setPid(1);
		//获取映射接口实现类
		DmCategoryMapper mapper=session.getMapper(DmCategoryMapper.class);
		mapper.insert(dc);
		//session.insert("com.yc.damai.dao.DmProductMapper.insert",dc);
		//不commit, 会话会在关闭自动回滚
		session.commit();
	}
	
	@Test
	public void test3() throws IOException {
		DmCategory dc = new DmCategory();
		dc.setId(45);
		dc.setCname("修改后的测试分类");
		dc.setPid(1);
		session.update("com.yc.damai.dao.DmProductMapper.update",dc);
		//不commit, 会话会在关闭自动回滚
		session.commit();
	}
	
	@Test
	public void test4() throws IOException {
		DmCategory dc = new DmCategory();
		dc.setId(45);
		dc.setCname("修改后的测试分类");
		dc.setPid(1);
		session.delete("com.yc.damai.dao.DmProductMapper.delete",dc);
		//不commit, 会话会在关闭自动回滚
		session.commit();
	}
	
	@Test
	public void test5() throws IOException {
		/**
		 * 1.先查出一个订单明细记录
		 * 2.查出该订单明细对应的商品信息
		 */
		//DmOrderitemMapper dom = session.getMapper(DmOrderitemMapper.class);
		//DmProductMapper dpm = session.getMapper(DmProductMapper.class);
   	   // DmOrderitem doi = dom.selectById(59); 
		//DmProduct dp = dpm.selectById(doi.getPid());
		
		/***
		 * 测试驱动 ==> 先写好所有的测试代码 ==> 再业务代码
		 */
		DmOrderitemMapper dom = session.getMapper(DmOrderitemMapper.class);
		DmOrderitem doi = dom.selectById(59); 
		DmProduct dp = doi.getDmProduct(); //调用dmProduct 属性的get方法
		System.out.println(dp);
		//不commit, 会话会在关闭自动回滚
		//session.commit();
		session.close();
	}
	
	@Test
	public void test6() throws IOException {
		/**
		 * 1.先查出一个订单明细记录
		 * 2.查出该订单明细对应的商品信息
		 */
		//DmOrderitemMapper dom = session.getMapper(DmOrderitemMapper.class);
		//DmProductMapper dpm = session.getMapper(DmProductMapper.class);
   	   // DmOrderitem doi = dom.selectById(59); 
		//DmProduct dp = dpm.selectById(doi.getPid());
		
		/***
		 * 测试驱动 ==> 先写好所有的测试代码 ==> 再业务代码
		 */
		DmOrdersMapper dos = session.getMapper(DmOrdersMapper.class);
		DmOrders doss =dos.selectById(68);
		DmOrderitem[] doi = doss.getDmOrderitem(); 
		System.out.println(doi);
		//不commit, 会话会在关闭自动回滚
		//session.commit();
		session.close();
	}
	
	
	@Test
	public void test7() throws IOException {
		DmCategoryMapper mapper = session.getMapper(DmCategoryMapper.class);
		List<DmCategory> dcList = mapper.selectAll();
		System.out.println("====1=====");
		DmCategory dc = dcList.get(1);
		System.out.println("====2====");
		Assert.assertEquals("鞋靴箱包", dc.getCname());
		System.out.println("===3===");
		Assert.assertEquals(6, dc.getChildren().size());
		System.out.println("===4===");
		
	}
	
	
	
	@Test
	public void test8() throws IOException {
		DmProductMapper mapper = session.getMapper(DmProductMapper.class);
		System.out.println("======================");
		mapper.selectByObj(null);
		DmProduct dp = new DmProduct();
		System.out.println("======================");
		mapper.selectByObj(dp);
		
		dp.setPdesc("测试");
		System.out.println("======================");
		mapper.selectByObj(dp);
		
		dp.setIsHot(-1);
		System.out.println("======================");
		mapper.selectByObj(dp);
		
		dp.setIsHot(1);
		System.out.println("======================");
		mapper.selectByObj(dp);
		
	}
	
	
	
	
	@Test
	public void test9() throws IOException {
		DmProductMapper mapper = session.getMapper(DmProductMapper.class);
		int[] cids = {1,2,3};
		mapper.selectByCids(cids);
	}
	
	@Test
	public void test10() throws IOException {
		DmProductMapper mapper = session.getMapper(DmProductMapper.class);
		DmProduct dp =new DmProduct();
		//只修改一个字段
		dp.setId(1);
		dp.setMarketPrice(885d);
		mapper.update(dp);
		//
		DmProduct dpdp = mapper.selectById(1);
		
		Assert.assertEquals((Double)885d, dpdp.getMarketPrice());
		Assert.assertEquals((Double)228d, dpdp.getShopPrice());
		Assert.assertEquals("韩版连帽加厚毛衣女外套", dpdp.getPname());
	}
	
	@Test
	public void test11() throws IOException {
		DmOrdersMapper dosm = session.getMapper(DmOrdersMapper.class);
		DmOrderitemMapper doim = session.getMapper(DmOrderitemMapper.class);
		
		//
		DmOrderitem doi1 =new DmOrderitem();
		doi1.setPid(1);
		doi1.setCount(1);
		doi1.setTotal(100d);
		DmOrderitem doi2 = new DmOrderitem();
		doi2.setPid(2);
		doi2.setCount(1);
		doi2.setTotal(200d);
		
		//
		DmOrders dos = new DmOrders();
		dos.setTotal(300d);
		dos.setAid(1);
		dos.setState(1);
		dos.setUid(1); 
		
		try {
			dosm.insert(dos);
			doi1.setOid(dos.getId());
			doi2.setOid(dos.getId());
			
			doim.insert(doi1); 
			doim.insert(doi2);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
	}
	
	
	
	
	
	
	
	
	//dm_product
	
	@Test
	public void Test1() throws IOException {
		DmProduct dp = new DmProduct();
		dp.setPname("秋季针织上衣");
		dp.setMarketPrice(92.0);
		dp.setShopPrice(88.0);
		dp.setImage("products/1/cs10011.jpg");
		dp.setPdesc("新款上市，亲们快速抢购哦~");
		dp.setIsHot(1);
		dp.setCid(8);
		session.insert("com.yc.damai.dao.DmProductMapper.insertproduct",dp);
		//不commit, 会话会在关闭自动回滚
		session.commit();
	}
	
	@Test
	public void Test2() throws IOException {
		DmProduct dp = new DmProduct();
		dp.setPname("秋季针织上衣");
		dp.setMarketPrice(42.0);
		dp.setShopPrice(48.0);
		dp.setId(74);
//		dp.setImage("products/1/cs10011.jpg");
//		dp.setPdesc("新款上市，亲们快速抢购哦~");
//		dp.setIsHot(1);
//		dp.setCid(8);
		session.update("com.yc.damai.dao.DmProductMapper.updateproduct",dp);
		//不commit, 会话会在关闭自动回滚
		session.commit();
	}
	
	@Test
	public void Test3() throws IOException {
		DmProduct dp = new DmProduct();
		dp.setId(73);
		session.delete("com.yc.damai.dao.DmProductMapper.deleteproduct",dp);
		//不commit, 会话会在关闭自动回滚
		session.commit();
	}
	
}
