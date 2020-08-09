package com.snack.dao;

import java.util.List;
import java.util.Map;

import com.snack.util.DBHelper;



public class PageDao {

	/**
	 * 分页查询
	 * @param page
	 * @param size
	 * @return
	 * limit 第几行，查几条记录
	 */
	public List<Map<String,Object>> queryPage(int page,int size){
		int begin=(page-1)*size; 
		return new DBHelper().query("select * from goodsinfo where status=1 limit ?, ?",begin,size);
		
	}
	
	/**
	 *统计总页数
	 */
	public int countPages(int size) {
		if(new DBHelper().count("select * from goodsinfo where status=1")%size==10) {
			return new DBHelper().count("select * from goodsinfo where status=1")/size;
		}
		return new DBHelper().count("select * from goodsinfo where status=1")/size+1;
		
	}
	
	
}
