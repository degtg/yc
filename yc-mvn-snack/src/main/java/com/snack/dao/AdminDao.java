package com.snack.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import com.snack.bean.AdminInfo;
import com.snack.util.DBHelper;

public class AdminDao {

	DBHelper db=new DBHelper();
	
	//查询管理员信息
	public List<Map<String,Object>> query(){
		String sql="select * from admininfo";
		return db.query(sql);
		
	}
	//通过id查询管理员信息
	public List<Map<String,Object>> queryAid(String aid ){
		String sql="select * from admininfo where aid=?";
		return db.query(sql,aid);
		
	}
	//模糊查询查询管理员信息
	public List<Map<String,Object>> query(String aname,String tel){
		String sql="select * from admininfo where 1=1";
		List<Object> params=new ArrayList<>();
		
		if(aname!=null && aname.trim().isEmpty()==false) {
			sql+=" and aname like concat('%',?,'%')";
			params.add(aname);
		}
		if(tel!=null && tel.trim().isEmpty()==false) {
			sql+=" and tel like concat('%',?,'%')";
			params.add(tel);
		}
		
		return db.query(sql, params.toArray());
		
	}
	//插入
	public void insert(String aname,String pwd,String tel) {
		String sql="insert into admininfo values(null,?,MD5(?),?,'正常')";
		db.update(sql, aname, pwd,tel);
		
	}
	
	public void delete(String aid) {
		String sql="delete from admininfo where aid=?";
		db.update(sql,aid);
	}
	
	public Map<String,Object> selectByLogin(String aname,String pwd) {
		String sql="select aid,aname,pwd from admininfo where aname=? and pwd=MD5(?)";
		List<Map<String,Object>> list=db.query(sql, aname,pwd);
		System.out.println("sql:"+sql);
		if(list.size()==0) {
			return null;
		}else {
			Map<String,Object> user=list.get(0);
			return user;
		}
		
	} 
	
	public List<Map<String ,Object>> getAdminItems(){
		String sql="select distinct status from admininfo ";
		return db.query( sql);
		
	}
	
	public void updateStatus(String aid,String status) {
		String sql="update admininfo set status=? where aid=?";
		db.update(sql, status, aid);
	}
	
	/**
	 * 保存管理员信息
	 */
	
	public void save(String aid,String aname,String pwd,String tel,String status,String photo,String date) {
		String sql="update admininfo set aname=?,pwd=?,tel=?,status=?,photo=?,date=? where aid=?";
		db.update(sql, aname,pwd,tel,status,photo,Date.valueOf(date),aid);
	}
	
	//添加管理员
			public void addAdmin(String aname,String pwd,String tel,String photo) {
				String sql="insert into admininfo values(null,?,MD5(?),?,'正常',?,now())";
				db.update(sql, aname, pwd,tel,photo);
				
			}

	
	/**
	 * 分页查询管理员
	 */
	
	public int count1(AdminInfo dp){
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getAname()!=null && dp.getAname().trim().isEmpty() == false) {
			where += " and aname like ?";
			params.add("%" + dp.getAname() + "%");
		}
		if(dp.getTel()!=null && dp.getTel().trim().isEmpty() == false) {
			where += " and tel like ?";
			params.add("%" + dp.getTel() + "%");
		}
		String sql = "select * from admininfo where 1=1" + where;
		return db.count(sql, params.toArray());
	}
	
	
	public List<Map<String,Object>> query1(AdminInfo dp, String page, String rows){
		
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getAname()!=null && dp.getAname().trim().isEmpty() == false) {
			where += " and aname like ?";
			params.add("%" + dp.getAname() + "%");
		}
		if(dp.getTel()!=null && dp.getTel().trim().isEmpty() == false) {
			where += " and tel like ?";
			params.add("%" + dp.getTel() + "%");
		}
		
		int ipage = Integer.parseInt(page);
		int irows = Integer.parseInt(rows);
		ipage = (ipage - 1) * 10;
		String sql = "select * from admininfo where 1=1 "
				+ where
				+ " limit ?, ?";
		params.add(ipage);
		params.add(irows);
		return db.query(sql, params.toArray());

	}

}
