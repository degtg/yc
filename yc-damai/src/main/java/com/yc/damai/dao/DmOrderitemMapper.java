package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.po.DmOrderitem;

public interface DmOrderitemMapper {
	List<DmOrderitem> selectAll();
	DmOrderitem selectById(int id);
	//DmOrderitem selectByOid(int oid);
	List<DmOrderitem> selectByOid(int oid);
	//新增订单明细
	int insert(DmOrderitem doi);
}
