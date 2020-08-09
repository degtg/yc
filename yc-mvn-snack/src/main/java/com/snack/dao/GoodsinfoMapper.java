package com.snack.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.snack.bean.GoodsInfo;

public interface GoodsinfoMapper {

	List<GoodsInfo> selectAll();
	GoodsInfo selectById(int gno);
	List<GoodsInfo> selectByObj(GoodsInfo gi);
	
	/**
	 * 根据枚举的分类id进行查询
	 */
	List<GoodsInfo> selectByCids(@Param("gnos") int[] gnos);
	
	int update(GoodsInfo gi);
}
