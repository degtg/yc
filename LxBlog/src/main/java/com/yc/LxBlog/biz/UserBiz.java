package com.yc.LxBlog.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.LxBlog.bean.User;
import com.yc.LxBlog.dao.UserMapper;

@Service
public class UserBiz {

	@Resource
	private UserMapper uMapper;
	
	public void register(User user) throws BizException {
		//可忽略字段验证
		//同名验证
		if(uMapper.countByAccount(user.getAccount())>0) {
			throw new BizException("该用户已经存在");
		}
		uMapper.insert(user);
	}
	
	public User login(User user) throws BizException {
		//可忽略字段验证
		//同名验证
		User dbuser=uMapper.selectByLogin(user);
		if(dbuser==null) {
			throw new BizException("该用户已经存在");
		}
		return dbuser;
	}
}
