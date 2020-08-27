package com.yc.cinema.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.cinema.dao.HallDao;
@Service
public class HallBiz {
	@Autowired
	private MovieBiz mbiz;
	@Autowired
	private HallDao hdao;

	public MovieBiz getMbiz() {
		return mbiz;
	}

	public void setMbiz(MovieBiz mbiz) {
		this.mbiz = mbiz;
	}

	public HallDao getHdao() {
		return hdao;
	}

	public void setHdao(HallDao hdao) {
		this.hdao = hdao;
	}
	
}
