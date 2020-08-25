package com.yc.LxBlog.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.yc.LxBlog.dao.ArticleMapper;

@Controller
public class IndexAction {
	@Resource
	private ArticleMapper amapper;

	@GetMapping("/")
	public String index(Model m,@RequestParam(defaultValue="1") int page) {
		//在执行查询前，设置分页参数
		//注意，必须是在查询方法之前，调用分页参数设置
		PageHelper.startPage(page,5);
		m.addAttribute("alist", amapper.selectByNew());
		return "index";
		
	}
	
	@GetMapping("article")
	public String article(int id,Model m) {
		m.addAttribute("article", amapper.selectById(id));
		return "article";
		
	}
	@GetMapping("addArticle")
	public String addArticle() {
		return "addArticle";
		
	}
}
