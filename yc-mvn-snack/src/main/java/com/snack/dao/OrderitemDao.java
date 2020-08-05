package com.snack.dao;

import java.util.List;

import com.snack.util.DBHelper;

public class OrderitemDao {
	public int insert(String mno) {
		String sql="INSERT into orderiteminfo\n" +
				"SELECT\n" +
				"	null,(select MAX(ono) FROM orderinfo),a.gno,a.num,b.price,0,a.cno\n" +
				"FROM\n" +
				"	cartinfo a\n" +
				"JOIN goodsinfo b ON a.gno = b.gno\n" +
				"WHERE\n" +
				"	mno = ?";
		return new DBHelper().update(sql, mno);
	}
	
	public List<?>queryByOnoCno(String ono,String cno){
		String sql="SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	orderiteminfo a\n" +
				"JOIN goodsinfo b ON a.gno = b.gno\n" +
				"WHERE\n" +
				"	a.ono = ? and a.cno=?";
		return new DBHelper().query(sql, ono,cno);
	
	} 
	public List<?>queryByOno(String ono){
		String sql="SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	orderiteminfo a\n" +
				"JOIN goodsinfo b ON a.gno = b.gno\n" +
				"WHERE\n" +
				"	a.ono = ? ";
		return new DBHelper().query(sql, ono);
	
	}
	
	
	
	public List<?>queryBygno(String gno){
		String sql="select * from goodsinfo where gno=?";
		return new DBHelper().query(sql, gno);
	
	}
}
