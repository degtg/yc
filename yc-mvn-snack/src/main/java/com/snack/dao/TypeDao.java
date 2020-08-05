package com.snack.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.snack.bean.GoodsType;
import com.snack.util.DBHelper;

public class TypeDao {
	
	
	public List<GoodsType> Qtype(String tno){
		String sql="select * from goodstype where tno=?";
		return new DBHelper().query(sql,GoodsType.class,tno);
		
	}

	public List<Map<String ,Object>>selectAll (){
		String sql="select * from goodstype";
		return new DBHelper().query(sql);
		
	}
	
	
	public List<Map<String,Object>> query(String tno,String tname){
		String sql="select * from goodstype where 1=1";
		List<Object> params=new ArrayList<>();
		if(tno!=null && tno.trim().isEmpty()==false) {
			sql+=" and tno like concat('%',?,'%')";
			params.add(tno);
		}
		if(tname!=null && tname.trim().isEmpty()==false) {
			sql+=" and tname like concat('%',?,'%')";
			params.add(tname);
		}
		
		return new DBHelper().query(sql, params.toArray());
		
	}
	
	/**
	 * 删除商品类型
	 */
	public void delType(String tno) {
		String sql="delete from goodstype where tno=?";
		new DBHelper().update(sql, tno);
	}
	
	/**
	 * 查询商品类型
	 * 
	 */
	
	public List<Map<String ,Object>> queryType(){
		String sql="select * from goodstype";
		return new DBHelper().query(sql);
		
	}
	
	/**
	 * 查询商品类型 去重
	 * @param id
	 * @return
	 */
	public List<Map<String ,Object>> getGoodstypeItems(){
		String sql="select distinct tno from goodsinfo ";
		return new DBHelper().query( sql);
		
	}

}
