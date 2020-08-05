package com.snack.dao;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.snack.bean.GoodsInfo;
import com.snack.util.DBHelper;

public class ProductDao {

	DBHelper db=new DBHelper();
	
	//查询所有商品
	public List<Map<String,Object>> queryAll(){
		String sql="select * from goodsinfo";
		return db.query(sql);
	}
	//通过商品编号查询所有商品
	public List<Map<String,Object>> Qtype(String tno){
		String sql="select * from goodsinfo where tno=?";
		return db.query(sql,tno);
	}
	
	public List<Map<String,Object>> queryAll1(){
		String sql="SELECT\r\n" + 
				"	a.gno,\r\n" + 
				"	a.gname,\r\n" + 
				"	b.tname,\r\n" + 
				"	a.price,\r\n" + 
				"	a.balance,\r\n" + 
				"	a.unit,\r\n" + 
				"	a.qperied,\r\n" + 
				"	a.weight,\r\n" + 
				"	a.`status`\r\n" + 
				"	FROM\r\n" + 
				"	goodsinfo a\r\n" + 
				"INNER JOIN goodstype b WHERE a.tno=b.tno ";
		 return new DBHelper().query(sql);
	}
	public List<Map<String,Object>> queryAll1(String tname,String gname){
		DBHelper dbh=new DBHelper();
		String sql="SELECT\r\n" + 
				"	a.gno,\r\n" + 
				"	a.gname,\r\n" + 
				"	b.tname,\r\n" + 
				"	a.price,\r\n" + 
				"	a.balance,\r\n" + 
				"	a.unit,\r\n" + 
				"	a.qperied,\r\n" + 
				"	a.weight,\r\n" + 
				"	a.`status`\r\n" + 
				"	FROM\r\n" + 
				"	goodsinfo a\r\n" + 
				"	INNER JOIN goodstype b  "
				+ "  where 1=1 and a.tno=b.tno" ;
		List<Object>params=new ArrayList<>();
		if(tname!=null&&tname.trim().isEmpty()==false) {
			sql+=" and tname like concat('%',?,'%') ";
			params.add(tname);
		}
		if(gname!=null&&gname.trim().isEmpty()==false) {
			sql+=" and gname like concat('%',?,'%') ";
			params.add(gname);
		}
		sql+=" order by gno asc limit 0,5";
		return dbh.query(sql, params.toArray());
	}
	public List<Map<String,Object>>getGTypeNO(){
		String sql="select distinct tno from goodstype";
		 return new DBHelper().query(sql);
	}
	public List<Map<String,Object>>getGTypeName(){
		String sql="select distinct tname from goodstype";
		 return new DBHelper().query(sql);
	}
	
	
	
	public List<Map<String,Object>> query(String gname){
		String sql="select * from goodsinfo where 1=1 ";
		List<Object> params=new ArrayList<>();
		if(gname!=null && gname.trim().isEmpty()==false) {
			sql+=" and gname like concat('%',?,'%')";
			params.add(gname);
			
		}
		return db.query(sql, params.toArray());
		
	}
	
	public List<Map<String,Object>> queryshowcomment(String content){
		String sql="select * from discuss where 1=1 and picture1!='' AND picture2!='' AND picture3!='' ";
		List<Object> params=new ArrayList<>();
		if(content!=null && content.trim().isEmpty()==false) {
			sql+=" and content like concat('%',?,'%')";
			params.add(content);
			
		}
		return db.query(sql, params.toArray());
		
	}
	
	public void deCart(String cno){
		String sql="delete from cartinfo where cno=? ";
		db.update(sql);
	}
	
	
	public void insert(String gname,String tno,String price,String intro,String balance,String pics,String unit,String qperied,String weight, String descr,Integer status) {
		String sql="insert into goodsinfo values(null,?,?,?,?,?,?,?,?,?,?,?)";
		db.update(sql, gname,tno,price,intro,balance,pics,unit,qperied,weight,descr,status.toString());
	}
	
	/**
	 * 添加商品类型
	 * @param tname
	 */
	public void insertType(String tname) {
		String sql="insert into goodstype values(null,?,1)";
		db.update(sql, tname);
	}
	
	/**
	 * 查询商品类型
	 */
	
	public List<Map<String,Object>> queryType(String tname){
		String sql="select * from goodstype where 1=1";
		List<Object> params=new ArrayList<>();
		
		if(tname!=null && tname.trim().isEmpty()==false) {
			sql+=" and tname like concat('%',?,'%')";
			params.add(tname);
		}
		
		return db.query(sql, params.toArray());
		
	}
	
	//分页查询  page为页号  size每页行数  limit 第几行 查几条记录
	public List<Map<String,Object>>queryPage(int page,int size){
		
		int begin=(page-1)*size;
		return new DBHelper().query("SELECT\n" +
				"	a.gno,\n" +
				"	a.gname,\n" +
				"	b.tname,\n" +
				"	a.price,\n" +
				"	a.balance,\n" +
				"	a.unit,\n" +
				"	a.qperied,\n" +
				"	a.weight,\n" +
				"	a.`status`\n" +
				"FROM\n" +
				"	goodsinfo a\n" +
				"INNER JOIN goodstype b WHERE a.tno=b.tno order by a.gno asc "
				+ " limit ?,?",
				begin,size);
	
	}
	
	public List<Map<String,Object>>queryPage(int page,int size,String tname,String gname){
		StringBuffer sb=new StringBuffer();
		System.out.println(tname+"==");
		System.out.println(gname+"==");
		sb.append("SELECT a.gno,a.gname,b.tname,a.price,a.balance,a.unit,a.qperied,a.weight,a.`status` ");
		sb.append(" FROM goodsinfo a INNER JOIN goodstype b ");
		int begin=(page-1)*size;
		if(tname!="") {
			sb.append(" WHERE a.tno=b.tno and b.tname =? ORDER BY a.gno ASC limit ?,? ");
			return new DBHelper().query(sb.toString(),tname,begin,size);
			
		}else if(gname!=""){
			sb.append(" WHERE a.tno=b.tno and a.gname =? ORDER BY a.gno ASC limit ?,? ");
			return new DBHelper().query(sb.toString(),gname,begin,size);
			
		}else if(tname!="" && gname!="") {
			sb.append(" WHERE a.tno=b.tno and a.gname =? and b.tname =? ORDER BY a.gno ASC limit ?,? ");
			return new DBHelper().query(sb.toString(),gname,tname,begin,size);
			
		}else {
			sb.append(" WHERE a.tno=b.tno  ORDER BY a.gno ASC limit ?,? ");
			return new DBHelper().query(sb.toString(),begin,size);
		}
		
	
	}
	/**
	 * 统计总有页数
	 * @param args
	 * @throws IOException
	 */
    public int countPages(int size) {
    	if(new DBHelper().count("SELECT\n" +
    			"	a.gno,\n" +
    			"	a.gname,\n" +
    			"	b.tname,\n" +
    			"	a.price,\n" +
    			"	a.balance,\n" +
    			"	a.unit,\n" +
    			"	a.qperied,\n" +
    			"	a.weight,\n" +
    			"	a.`status`\n" +
    			"FROM\n" +
    			"	goodsinfo a\n" +
    			"INNER JOIN goodstype b WHERE a.tno=b.tno ")%size==0) {
    		return new DBHelper().count("SELECT\n" +
    				"	a.gno,\n" +
    				"	a.gname,\n" +
    				"	b.tname,\n" +
    				"	a.price,\n" +
    				"	a.balance,\n" +
    				"	a.unit,\n" +
    				"	a.qperied,\n" +
    				"	a.weight,\n" +
    				"	a.`status`\n" +
    				"FROM\n" +
    				"	goodsinfo a\n" +
    				"INNER JOIN goodstype b WHERE a.tno=b.tno ")/size;
    	}
    	return new DBHelper().count("SELECT\n" +
    			"	a.gno,\n" +
    			"	a.gname,\n" +
    			"	b.tname,\n" +
    			"	a.price,\n" +
    			"	a.balance,\n" +
    			"	a.unit,\n" +
    			"	a.qperied,\n" +
    			"	a.weight,\n" +
    			"	a.`status`\n" +
    			"FROM\n" +
    			"	goodsinfo a\n" +
    			"INNER JOIN goodstype b WHERE a.tno=b.tno ")/size+1;
    }
		
    
    public int countPages(int size,String tname,String gname) {
    	StringBuffer sb=new StringBuffer();
    	sb.append("select count(*) cnt from (SELECT a.gno,a.gname,b.tname,a.price,a.balance,a.unit,a.qperied,a.weight,a.`status` ");
    	sb.append(" FROM goodsinfo aINNER JOIN goodstype b WHERE a.tno=b.tno ");
    	if(tname!="") {
    		sb.append(" and b.tname=?) a" );
    		if(new DBHelper().count(sb.toString(),tname)%size==0) {
        		return new DBHelper().count(sb.toString(),tname)/size;
        	}
        	return new DBHelper().count(sb.toString(),tname)/size+1;
    		
    	}else if(gname!="") {
    		sb.append(" and a.gname=?) a");
    		if(new DBHelper().count(sb.toString(),gname)%size==0) {
        		return new DBHelper().count(sb.toString(),gname)/size;
        	}
        	return new DBHelper().count(sb.toString(),gname)/size+1;
    	}else if(tname!="" && gname!="") {
    		sb.append(" and b.tname=? and a.gname=?) a");
    		if(new DBHelper().count(sb.toString(),tname,gname)%size==0) {
        		return new DBHelper().count(sb.toString(),tname,gname)/size;
        	}
        	return new DBHelper().count(sb.toString(),tname,gname)/size+1;
    	}else {
    		if(new DBHelper().count(sb.toString())%size==0) {
        		return new DBHelper().count(sb.toString())/size;
        	}
        	return new DBHelper().count(sb.toString())/size+1;
    	}
    	
		
	}
    
    /**
	 * 新品推荐
	 */
	public List<Map<String,Object>> queryNewPut(){
		String sql="select * from goodsinfo where `status`=0 ORDER BY gno LIMIT 0,2";
		return db.query(sql);
		
	}
	
	/**
	 * 商品详情查询
	 */
	public List<Map<String,Object>> queryDetail(String gno){
		String sql="select * from goodsinfo where gno=?";
		return db.query(sql, gno);
		
	}
    
	/**
	 * 最近浏览
	 */
	
	public Map<String,Object> queryid1(String gno) {
		String sql="";
		if(gno.equalsIgnoreCase("undefined")) {
			 sql = "select * from goodsinfo ";
			 return db.query(sql).get(0);
		}else {
			 sql = "select * from goodsinfo where gno=?";
			
			System.out.println(gno+"========");
			return db.query(sql, gno).get(0);
		}
		

	}
	
	/***
	 * 聚划算优品
	 */
	public List<Map<String ,Object>> queryCheaper(){
		String sql="select * from goodsinfo where status=2";
		return db.query(sql);
		
	}
	
	/*
	 * 零食礼包
	 */
	public List<Map<String ,Object>> queryGift(){
		String sql="select * from newinfo";
		return db.query(sql);
		
	}

	/**
	 * 
	 * 
	 * 分页查询所有商品
	 */
	public int count1(GoodsInfo dp){
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getGname()!=null && dp.getGname().trim().isEmpty() == false) {
			where += " and gname like ?";
			params.add("%" + dp.getGname() + "%");
		}
		if(dp.getIntro()!=null && dp.getIntro().trim().isEmpty() == false) {
			where += " and intro like ?";
			params.add("%" + dp.getIntro() + "%");
		}
		String sql = "select * from goodsinfo where 1=1" + where;
		return db.count(sql, params.toArray());
	}
	
	
	public List<Map<String,Object>> query1(GoodsInfo dp, String page, String rows){
		
		String where = "";
		List<Object> params = new ArrayList<>();
		if(dp.getGname()!=null && dp.getGname().trim().isEmpty() == false) {
			where += " and gname like ?";
			params.add("%" + dp.getGname() + "%");
		}
		if(dp.getIntro()!=null && dp.getIntro().trim().isEmpty() == false) {
			where += " and intro like ?";
			params.add("%" + dp.getIntro() + "%");
		}
		
		int ipage = Integer.parseInt(page);
		int irows = Integer.parseInt(rows);
		ipage = (ipage - 1) * 10;
		String sql = "select * from goodsinfo where 1=1 "
				+ where
				+ " limit ?, ?";
		params.add(ipage);
		params.add(irows);
		return db.query(sql, params.toArray());

	}
	
	
	/**
	 * 保存商品
	 */
	
	public void save(String gno,String gname,String tno,String price,String intro,String balance,String pics,String unit,String qperied,String weight,String descr,String status) {
		String sql="update goodsinfo set gname=?,tno=?,price=?,intro=?,balance=?,pics=? ,unit=?,qperied=?,weight=?,descr=?,status=? where gno=?";
		db.update(sql, gname,tno,price,intro,balance,pics,unit,qperied,weight,descr,status,gno);
	}
	
	/**
	 * 删除商品
	 */
	
	public void delete(String gno) {
		String sql="delete from goodsinfo where gno=?";
		db.update(sql,gno);
	}

	
	
}
