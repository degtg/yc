package com.snack.dao;

import java.util.List;

import com.snack.bean.GoodsType;

public interface GoodstypeMapper {

	List<GoodsType> selsectAll();
	int insert(GoodsType gt);
	int update(GoodsType gt);
	int delete(int tno);
}
