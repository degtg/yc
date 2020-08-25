package com.yc.LxBlog.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.LxBlog.bean.Article;
import com.yc.LxBlog.dao.ArticleMapper;
@Service
public class ArticleBiz {

	@Resource
	private ArticleMapper aMapper;
	
	public int create(Article art) {
		return aMapper.insert(art);
		
	}
}
