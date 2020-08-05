package com.snack.dao;

import java.util.List;
import java.util.Map;

import com.snack.util.DBHelper;

public class AddressDao {

	DBHelper db=new DBHelper();
	
	public void insert(String mno,String name,String tel,String province,String city,String area,String addr) {
		String sql="insert into addrinfo values(null,?,?,?,?,?,?,?,0,1)";
		db.update(sql, mno,name,tel,province,city,area,addr);
	}
	public int delByano(String ano){
		String sql="delete  from addrinfo where ano=?";
		return new DBHelper().update(sql, ano);
	}
	
	public List<Map<String,Object>> queryAddress(String mno){
		String sql="select * from addrinfo where mno=?";
		return db.query(sql, mno);
	}
	//查询某用户是否存在默认地址若存在则必须取消默认地址 否则不能设置默认地址
	public List<Map<String,Object>> queryAddressFlag(String mno){
		String sql="select * from addrinfo where mno=? and flag=1";
		return db.query(sql, mno);
	}
	//设置默认地址
	public void update(String mno,String ano) {
		String sql="update addrinfo set flag=1 where mno=? and ano=?";
		db.update(sql, mno,ano);
	}
	//取消设置默认地址
		public void Cancel(String mno,String ano) {
			String sql="update addrinfo set flag=0 where mno=? and ano=?";
			db.update(sql, mno,ano);
		}
}
