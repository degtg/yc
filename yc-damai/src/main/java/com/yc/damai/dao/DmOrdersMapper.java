package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.po.DmOrders;

public interface DmOrdersMapper {
	List<DmOrders> selectAll();
	DmOrders selectById(int id);
	
	//新增订单
	
	int insert(DmOrders dos);
	
	

}
