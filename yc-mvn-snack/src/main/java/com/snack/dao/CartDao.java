package com.snack.dao;

import java.util.List;
import java.util.Map;

import com.snack.util.DBHelper;

public class CartDao {
	//给某用户添加新的购物商品
	public int insert(Object mno,String gno,String num) {
		String sql="insert into cartinfo values(null,?,?,?)";
		return new DBHelper().update(sql, mno,Integer.valueOf(gno),Integer.valueOf(num));
	}
	
	/**
	 * 给某个用户的商品数量加num
	 * @param mno
	 * @param gno
	 * @return 更新的记录数
	 */
	public int update(Object mno,String gno,String num) {
		String sql="update cartinfo set num=num+? where mno=? and gno=?";
		return new DBHelper().update(sql,Integer.valueOf(num),mno,gno);
	}
	
	public List<Map<String, Object>>query(String mno,String gno){
		String sql="SELECT * from cartinfo where mno=? and gno=?";
		return new DBHelper().query(sql, mno,gno);
	}
	//查询所有已添加的购物车的商品数
	public List<?>queryNum(Object mno) {
		String sql="SELECT SUM(a.num)b from (select * from cartinfo where mno=?)a";
		return new DBHelper().query(sql, mno);
	}
	
	public List<?>queryByMno(Object mno){
		String sql="select * from cartinfo a join goodsinfo b on a.gno=b.gno where a.mno=?";
		return new DBHelper().query(sql, mno);
	}
	
	public int delByCno(String cno){
		String sql="delete  from cartinfo where cno=?";
		return new DBHelper().update(sql, cno);
	}
	public int delByMno(String mno){
		String sql="delete  from cartinfo where mno=?";
		return new DBHelper().update(sql, mno);
	}
	public int update1(String num,String mno,String gno) {
		String sql="update cartinfo set num=?-1 where mno=? and gno=?";
		return new DBHelper().update(sql,num,mno,gno);
	}
	
	public int update2(String num,String mno,String gno) {
		String sql="update cartinfo set num=?+1 where mno=? and gno=?";
		return new DBHelper().update(sql,num,mno,gno);
	}
	//查询购物车商品价格和总件数
	public List<Map<String, Object>>query1(String cno,String mno){
		String sql="select  sum(a.num*b.price)a,SUM(num)b from cartinfo a join goodsinfo b on a.gno=b.gno where a.cno=? and a.mno=?  ";
		return new DBHelper().query(sql, Integer.valueOf(cno),Integer.valueOf(mno));
	}
}
