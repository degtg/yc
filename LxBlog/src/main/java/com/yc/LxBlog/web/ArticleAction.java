package com.yc.LxBlog.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.yc.LxBlog.bean.Article;
import com.yc.LxBlog.bean.User;
import com.yc.LxBlog.biz.ArticleBiz;
import com.yc.LxBlog.dao.ArticleMapper;
import com.yc.LxBlog.util.Utils;

@RestController
public class ArticleAction {

	@Resource
	private ArticleBiz abiz;
	
	@Resource
	private ArticleMapper amapper;
	
	@GetMapping("article")
	public ModelAndView article(int id,ModelAndView m) {
		m.addObject("article", amapper.selectById(id));
		m.setViewName("article");
		return m;
		
	}
	@GetMapping("toAddArticle")
	public ModelAndView toAddArticle(ModelAndView mav) {
		mav.setViewName("addArticle");
		return mav;
	}

	@PostMapping("addArticle.do")
	public ModelAndView addArticle(@Valid Article a, Errors errors, ModelAndView mav,
			@SessionAttribute("loginedUser") User user) {
		// ModelAndView = Model + View
		if (errors.hasErrors()) {
			mav.addObject("errors", Utils.asMap(errors));
			mav.addObject("article", a);
			mav.setViewName("addArticle");
		} else {
			a.setAuthor(user.getName());
			abiz.create(a);
			//a.id==>有值==》数据库的自增列==》mybatis==>options
			mav.setViewName("redirect:article?id="+a.getId()); // 未完待续 .. 展示新添加的文章
		}
		return mav;
	}
}
