package com.yc.cinema.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.cinema.dao.ActorDao;
import com.yc.cinema.dao.MovieActorDao;
import com.yc.cinema.dao.MovieImageDao;
import com.yc.cinema.dao.MovieTypeDao;
@Service
public class MovieBiz {
	@Autowired
	private MovieActorDao madao;
	@Autowired
	private ActorDao adao;
	@Autowired
	private MovieImageDao midao;
	@Autowired
	private MovieTypeDao mtdao;

	public MovieActorDao getMadao() {
		return madao;
	}

	public void setMadao(MovieActorDao madao) {
		this.madao = madao;
	}

	public ActorDao getAdao() {
		return adao;
	}

	public void setAdao(ActorDao adao) {
		this.adao = adao;
	}

	public MovieImageDao getMidao() {
		return midao;
	}

	public void setMidao(MovieImageDao midao) {
		this.midao = midao;
	}

	public MovieTypeDao getMtdao() {
		return mtdao;
	}

	public void setMtdao(MovieTypeDao mtdao) {
		this.mtdao = mtdao;
	}

}
