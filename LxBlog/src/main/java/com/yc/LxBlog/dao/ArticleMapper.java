package com.yc.LxBlog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yc.LxBlog.bean.Article;

public interface ArticleMapper {

	@Select("select * from article order by createtime desc")
	public List<Article> selectByNew();
}
