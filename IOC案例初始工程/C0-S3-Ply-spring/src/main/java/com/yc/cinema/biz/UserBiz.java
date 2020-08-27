package com.yc.cinema.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.cinema.bean.User;
import com.yc.cinema.dao.UserDao;
@Service
public class UserBiz {
	@Autowired
	private UserDao udao;

	public User login(String name, String pwd) {
		return null;
	}

	public void register(User user) throws BizException {

	}

	public UserDao getUdao() {
		return udao;
	}

	public void setUdao(UserDao udao) {
		this.udao = udao;
	}

}
