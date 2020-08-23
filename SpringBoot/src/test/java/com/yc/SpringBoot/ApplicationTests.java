package com.yc.SpringBoot;

import javax.annotation.Resource;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.yc.SpringBoot.biz.MailService;
import com.yc.SpringBoot.dao.ProductMapper;

@SpringBootTest
class ApplicationTests {

	@Resource
	ProductMapper pm;
	@Resource
	MailService ms;
	
	@Test
	void contextLoads() {
		Assert.isTrue(pm.selectAll().size()>0,"没有数据");
	}
	
	@Test
	void testMail() {
		ms.sendSimpleMail("1572834916@qq.com", "邮件清理", "邮箱小助手提醒您，您的邮件过多，请及时清理！！");
	}
	
	

}
