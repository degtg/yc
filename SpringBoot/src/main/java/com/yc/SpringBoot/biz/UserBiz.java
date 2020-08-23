package com.yc.SpringBoot.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.SpringBoot.dao.UserDao;
import com.yc.damai.bean.DmUser;

@Service
public class UserBiz {
	
	@Resource
	private UserDao udao;

	public DmUser login(String name,String pwd) throws Exception {
		DmUser user=udao.selectByLogin(name,pwd);
		if(user==null) {
			throw new Exception("用户名或密码错误");
		}
		return user;
		
	}
}
