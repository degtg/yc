package com.snack.dao;

import java.util.List;

import com.snack.bean.GoodsInfo;
import com.snack.bean.OrderItemInfo;

public interface OrderitemMapper {

	List<OrderItemInfo> selectAll();
	OrderItemInfo selectById(int ono);
	int insert(OrderItemInfo oii);
}
