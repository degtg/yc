package com.yc.cinema.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.cinema.biz.ActorBiz;
import com.yc.cinema.biz.CommentsBiz;
import com.yc.cinema.biz.UserBiz;
@Controller
public class MovieAction {
	@Autowired
	private CommentsBiz cbiz;
	@Autowired
	private ActorBiz abiz;
	@Autowired
	private UserBiz ubiz;

	public CommentsBiz getCbiz() {
		return cbiz;
	}

	public void setCbiz(CommentsBiz cbiz) {
		this.cbiz = cbiz;
	}

	public ActorBiz getAbiz() {
		return abiz;
	}

	public void setAbiz(ActorBiz abiz) {
		this.abiz = abiz;
	}

	public UserBiz getUbiz() {
		return ubiz;
	}

	public void setUbiz(UserBiz ubiz) {
		this.ubiz = ubiz;
	}

}
