package com.snack.dao;
import java.util.List;
import java.util.Map;

import com.snack.util.DBHelper;
public class PersonDao {
	public void insert(String pname,String pnname,String sex,String area,String mno) {
		String sql = "insert into personinfo  values(?,?,?,?,?)";
		new DBHelper( ).update(sql, pname,pnname,sex,area,mno);
	}
	public void save(String pname,String pnname,String sex,String area,String mno) {
		String sql = "update personinfo set realName=?,nickName=?,sex=?,area=? where mno=?";
		new DBHelper( ).update(sql, pname,pnname,sex,area,mno);
	}
	public List<Map<String,Object>>query(String mno ){
		DBHelper dbh = new DBHelper( );
		String sql = "select * from personinfo where mno=?";
		return dbh.query(sql,mno);
	}
}
