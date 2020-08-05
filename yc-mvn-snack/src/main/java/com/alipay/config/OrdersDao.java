package com.alipay.config;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.snack.bean.OrderInfo;
import com.snack.util.DBHelper;

public class OrdersDao {
	//添加订单主表记录 orders
	public int insert(String mno) {
		String sql="INSERT INTO orderinfo SELECT\n" +
				"	null,\n" +
				"	NOW(),\n" +
				"	b.ano,\n" +
				"	NULL,\n" +
				"	NULL,\n" +
				"	0,\n" +
				"	c.total,\n" +
				"	NULL\n" +
				" FROM\n" +
				"	memberinfo a\n" +
				//获取默认地址
				"JOIN addrinfo b ON a.mno = b.mno\n" +
				"AND flag = 1\n" +
				"JOIN (\n" +
				"	SELECT\n" +
				"		SUM(b.price * a.num) total,\n" +
				"		a.mno \n" +
				"	FROM\n" +
				"		cartinfo a\n" +
				"	JOIN goodsinfo b ON a.gno = b.gno\n" +
				"	WHERE\n" +
				"		a.mno = ?\n" +
				"	GROUP BY\n" +
				"		a.mno\n" +
				") c ON a.mno = c.mno\n" +
				"WHERE\n" +
				"	a.mno = ? ";
		return new DBHelper().update(sql, mno,mno);
	}
	//查询支付商品订单
	public Map<String,Object>queryPayOrders(String mno){
		String sql="SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	orderinfo a\n" +
				"JOIN addrinfo b ON a.ano = b.ano\n" +
				"WHERE\n" +
				"	b.mno=?  \n" +
				"AND a.STATUS = 1\n" +
				"ORDER BY\n" +
				"	ono DESC\n"+
				"LIMIT 0,\n" +
				" 1";
		List<Map<String,Object>>list=new DBHelper().query(sql, mno );
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}
	//查询未支付的订单
	public Map<String,Object>queryNewOrders(String mno){
		String sql="SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	orderinfo a\n" +
				"JOIN addrinfo b ON a.ano = b.ano\n" +
				"WHERE\n" +
				"	b.mno=?  \n" +
				"AND a.STATUS = 0\n" +
				"ORDER BY\n" +
				"	ono DESC\n" +
				"LIMIT 0,\n" +
				" 1";
		List<Map<String,Object>>list=new DBHelper().query(sql, mno );
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}
	
	//查询待发货的订单详情
		public Map<String,Object>queryshouhuo(String mno){
			String sql="SELECT\n" +
					"	*\n" +
					"FROM\n" +
					"	orderinfo a\n" +
					"JOIN addrinfo b ON a.ano = b.ano\n" +
					"WHERE\n" +
					"	b.mno=?  \n" +
					"AND a.STATUS = 2\n" +
					"ORDER BY\n" +
					"	ono DESC\n"+
					"LIMIT 0,\n" +
					" 1";
			List<Map<String,Object>>list=new DBHelper().query(sql, mno );
			if(list.isEmpty()) {
				return null;
			}else {
				return list.get(0);
			}
		}
		//查询待评价商品订单
		public Map<String,Object>queryWaitCommentOrders(String mno){
			String sql="SELECT\n" +
					"	*\n" +
					"FROM\n" +
					"	orderinfo a\n" +
					"JOIN addrinfo b ON a.ano = b.ano\n" +
					"WHERE\n" +
					"	b.mno=?  \n" +
					"AND a.STATUS = 3\n" +
					"ORDER BY\n" +
					"	ono DESC\n"+
					"LIMIT 0,\n" +
					" 1";
			List<Map<String,Object>>list=new DBHelper().query(sql, mno );
			if(list.isEmpty()) {
				return null;
			}else {
				return list.get(0);
			}
		}
		
		/**
		 * 查询所有订单状态为1的订单
		 */
		public List<Map<String,Object>> queryPayOrder(){
			String sql="select * from orderinfo where status=1";
			return new DBHelper().query(sql);
			
		}
		
		
		/**
		 * 查询所有订单
		 */
		public List<Map<String,Object>> queryAllOrder(){
			String sql="select * from orderinfo";
			return new DBHelper().query(sql);
			
		}
		
		
		
		
		
		public List<Map<String,Object>> queryOrder(String price,String status,String odate){
			String sql="select * from orderinfo where 1=1";
			List<Object> params=new ArrayList<>();
			
			if(price!=null && price.trim().isEmpty()==false) {
				sql+=" and price like concat('%',?,'%')";
				params.add(price);
			}
			if(status!=null && status.trim().isEmpty()==false) {
				sql+=" and status like concat('%',?,'%')";
				params.add(status);
			}
			if(odate!=null && odate.trim().isEmpty()==false) {
				Date d=Date.valueOf(odate);
				sql+=" and odate=?";
				params.add(d);
			}
			return new DBHelper().query(sql, params.toArray());
			
		}
		
		/**
		 * 查订单状态字段 去重
		 * @param id
		 * @return
		 */
		public List<Map<String ,Object>> getStatusItems(){
			String sql="select distinct status from orderinfo ";
			return new DBHelper().query( sql);
			
		}
		
		/**
		 * 删除订单
		 */
		public void delete(String ono) {
			String sql="delete from orderinfo where ono=?";
			new DBHelper().update(sql, ono);
		}
		
		/**
		 * 保存订单
		 */
		public void save(String ono,String odate,String ano,String sdate,String rdate,String status,String price,String invoice) {
			String sql="update orderinfo set odate=?,ano=?,sdate=?,"+"rdate=?,status=?,price=?,invoice=? where ono=?";
			new DBHelper().update(sql, Date.valueOf(odate),ano,Date.valueOf(sdate),Date.valueOf(rdate),status,price,invoice,ono);
		}
		
		
		
		/**
		 * 发货
		 */
		public void updateStatus(String ono) {
			String sql="update orderinfo set status=2 where ono=?";
			new DBHelper().update(sql, ono);
		}
		
		//更新商品状态为已收货的状态变为3
		public int updateStatus3(String ono) {
			String sql = "UPDATE orderinfo set status=3 where ono=?";
			return new DBHelper().update(sql, ono);
		}
		
		/**
		 * 商品下架
		 */
		public void updateStatusdown(String gno) {
			String sql="update goodsinfo set status=0 where gno=?";
			new DBHelper().update(sql, gno);
		}
		
		/**
		 * 商品下架
		 */
		public void updatetypedown(String tno) {
			String sql="update goodstype set status=0 where tno=?";
			new DBHelper().update(sql, tno);
		}
		/**
		 * 订单评价
		 */
		public 	List<Map<String,Object>> queryOrDis(String ono){
			String sql="SELECT\r\n" + 
					"	*\r\n" + 
					"FROM\r\n" + 
					"	orderiteminfo a\r\n" + 
					"JOIN goodsinfo b ON a.gno = b.gno\r\n" + 
					"WHERE\r\n" + 
					"	a.ono = ?";
			return new DBHelper().query(sql, ono);
			
		}
		
		
		/**
		 * 订单分页查询
		 */
		
		public int count1(OrderInfo dp){
			String where = "";
			List<Object> params = new ArrayList<>();
			if(dp.getOno()!=null && dp.getOno().trim().isEmpty() == false) {
				where += " and ono like ?";
				params.add("%" + dp.getOno() + "%");
			}
			if(dp.getPrice() !=0) {
				where += " and price = ?";
				params.add(dp.getPrice());
			}
			
			String sql = "select * from orderinfo where 1=1" + where;
			return new DBHelper().count(sql, params.toArray());
		}
		
		
		public List<Map<String,Object>> query1(OrderInfo dp, String page, String rows){
			
			String where = "";
			List<Object> params = new ArrayList<>();
			if(dp.getOno()!=null && dp.getOno().trim().isEmpty() == false) {
				where += " and ono like ?";
				params.add("%" + dp.getOno() + "%");
			}
			if(dp.getPrice() !=0) {
				where += " and price = ?";
				params.add(dp.getPrice());
			}
			
			int ipage = Integer.parseInt(page);
			int irows = Integer.parseInt(rows);
			ipage = (ipage - 1) * 10;
			String sql = "select * from orderinfo where 1=1 "
					+ where
					+ " limit ?, ?";
			params.add(ipage);
			params.add(irows);
			return new DBHelper().query(sql, params.toArray());

		}

}
