package com.yc.cinema.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.cinema.dao.ActorDao;
@Service
public class ActorBiz {
	@Resource
	private ActorDao adao;

	public ActorDao getAdao() {
		return adao;
	}

	public void setAdao(ActorDao adao) {
		this.adao = adao;
	}
	
}
