package com.snack.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.snack.bean.MemberInfo;
import com.snack.util.DBHelper;

public class UserDao {

	DBHelper db=new DBHelper();
	MemberInfo member=new MemberInfo();
	public void insert(String nickName,String pwd,String tel,String email,String status) {
		String sql="insert into memberinfo values(null,?,null,MD5(?),?,?,null,now(),?)";
		db.update(sql, nickName, pwd,tel,email,status);
		
	}
	public Map<String,Object> selectByLogin(String nickName,String pwd) {
		String sql="select * from memberinfo where nickname=? and pwd=MD5(?)";
		List<Map<String,Object>> list=db.query(sql, nickName,pwd);
		if(list.size()==0) {
			return null;
		}else {
			Map<String,Object> user=list.get(0);
			return user;
		}
	} 
	
	
	public List<Map<String,Object>> query(String nickName,String tel){
		String sql="select * from memberinfo where 1=1";
		List<Object> params=new ArrayList<>();
		if(nickName!=null && nickName.trim().isEmpty()==false) {
			sql+=" and nickName like concat('%',?,'%')";
			params.add(nickName);
		}
		if(tel!=null && tel.trim().isEmpty()==false) {
			sql+=" and tel like concat('%',?,'%')";
			params.add(tel);
		}
		
		return db.query(sql, params.toArray());
	}
	
	//查询该用户的昵称是否存在
	
	public List<Map<String,Object>> queryBynickName(String nickName){
		String sql="select * from  memberinfo where nickName=?"; 
		return db.query(sql,nickName);
	}
	
	//查询该用户的由邮箱是否存在
	
		public List<Map<String,Object>> queryByemail(String email){
			String sql="select * from  memberinfo where email=?";
			return db.query(sql,email);
		}
	
	
	
	
	public void delete(String mno) {
		String sql="delete from memberinfo where mno=?";
		db.update(sql,mno);
	}
	
	
	public void updateStatus(String mno,String status) {
		String sql="update memberinfo set status=? where mno=?";
		db.update(sql, status, mno);
	}
	//忘记密码-查找用户和邮箱是否存在
	public Map<String,Object> SelectByMember(String realName,String email) {
		String sql="select * from memberinfo where realName=? and email=?";
		List<Map<String,Object>> list=db.query(sql,realName,email);
		if(list.size()==0) {
			return null;
		}else {
			Map<String,Object> user=list.get(0);
			return user;
		}
	} 
	//update memberinfo set pwd=MD5('1') where realName='小和尚' and email='2344244@qq.com'
	
	public void updateMemberPwd(String cpwd,String realName,String email) {
		String sql="update memberinfo set pwd=MD5(?) where realName=? and email=? ";
		db.update(sql, cpwd,realName, email);
	}
	//个人信息修改密码
	public void modifypwd(String pwd,String mno) {
		String sql="update memberinfo set pwd=MD5(?) where mno=? ";
		new DBHelper().update(sql,pwd,mno);
	}
	
	
	
	
	/**
	 * 查找用户头像
	 */
	public List<Map<String,Object>> queryHead(String mno){
		String sql="select * from memberinfo where mno=?";
		return db.query(sql,mno);
		
	}
	
	/**
	 * 更新头像
	 */
	public void updateHead(String photo,String nickName) {
		String sql="update memberinfo set photo=? where nickName=?";
		db.update(sql, photo,nickName);
		
	}
	
	
	public void changehead(String mno,String photo) {
		  String sql="update memberinfo set photo=? where mno=?";
		  db.update(sql, photo,mno);
	  }
	
	/**
	 * 保存用户信息
	 */
	
	public void save(String mno,String nickName,String realName,String pwd,String tel,String email,String photo,String regDate,String status) {
		String sql="update memberinfo set nickName=?,realName=?,pwd=?,tel=?,email=?,photo=?,regDate=?,status=? where mno=?";
		db.update(sql, nickName,realName,pwd,tel,email,photo,Date.valueOf(regDate),status,mno);
	}
	
	
	/**
	 * 分页查询用户信息
	 */
	public int count1(MemberInfo dp){
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getNickName()!=null && dp.getNickName().trim().isEmpty() == false) {
			where += " and nickName like ?";
			params.add("%" + dp.getNickName() + "%");
		}
		if(dp.getTel()!=null && dp.getTel().trim().isEmpty() == false) {
			where += " and tel like ?";
			params.add("%" + dp.getTel() + "%");
		}
		String sql = "select * from memberinfo where 1=1" + where;
		return db.count(sql, params.toArray());
	}
	
	
	public List<Map<String,Object>> query1(MemberInfo dp, String page, String rows){
		
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getNickName()!=null && dp.getNickName().trim().isEmpty() == false) {
			where += " and nickName like ?";
			params.add("%" + dp.getNickName() + "%");
		}
		if(dp.getTel()!=null && dp.getTel().trim().isEmpty() == false) {
			where += " and tel like ?";
			params.add("%" + dp.getTel() + "%");
		}
		
		int ipage = Integer.parseInt(page);
		int irows = Integer.parseInt(rows);
		ipage = (ipage - 1) * 10;
		String sql = "select * from memberinfo where 1=1 "
				+ where
				+ " limit ?, ?";
		params.add(ipage);
		params.add(irows);
		return db.query(sql, params.toArray());

	}


}
