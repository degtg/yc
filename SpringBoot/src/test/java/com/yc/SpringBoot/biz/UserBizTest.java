package com.yc.SpringBoot.biz;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import com.yc.SpringBoot.dao.UserDao;
import com.yc.damai.bean.DmUser;

@SpringBootTest
public class UserBizTest {
	/**
	 * 虚拟的UserDao对象（Mock对象）
	 */
	@MockBean
	private UserDao udao;
	@Resource
	private UserBiz ubiz;

	@Test
	public void testLogin() {
		try {
			//模拟的结果对象
			DmUser du=new DmUser();
			//模拟对象方法调用结果
			Mockito.when(udao.selectByLogin("sisi", "123")).thenReturn(du);
			DmUser user=ubiz.login("sisi", "123");
			Assert.isTrue(user!=null,"没找到该用户");
			System.out.println("测试完成");
		} catch (Exception e) {
			Assert.isTrue(e==null,"dao类异常");
			e.printStackTrace();
		}
		
	}
}
