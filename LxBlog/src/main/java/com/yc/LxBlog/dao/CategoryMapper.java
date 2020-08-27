package com.yc.LxBlog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yc.LxBlog.bean.Category;

public interface CategoryMapper {

	@Select("select * from category")
	public List<Category> selectAll();
	
	@Select("select * from Category where id=#{id}")
	public Category selectById(int id);
}
