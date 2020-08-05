package com.snack.dao;

import java.util.List;
import java.util.Map;

import com.snack.bean.OrderItemInfo;
import com.snack.util.DBHelper;

public class DiscussDao {

	/*
	 * public List<Map<String,Object>> query(String gno){ String
	 * sql="SELECT * FROM discuss a ,goodsinfo b , memberinfo c WHERE a.gno=b.gno AND a.mno=c.mno AND a.gno=?"
	 * ; return new DBHelper().query(sql, gno);
	 * 
	 * }
	 */
	//查询所有评论
	public List<Map<String,Object>> queryAll(){
		String sql="SELECT * FROM discuss WHERE picture1!='' AND picture2!='' AND picture3!='' ";
		return new DBHelper().query(sql);
		
	}
		public List<Map<String,Object>> query(String mno,String gno){
			String sql="select * from discuss where mno=? and gno=?";
			return new DBHelper().query(sql,mno,gno);
			
		}
		public void insert(String mno,String nickName,String content,String photo,String gno) {
			String sql="insert into discuss values(?,?,?,?,now(),?,null,null,null)";
			new DBHelper().update(sql,mno,nickName,content,photo,gno);
		}
		
		/**
		 * 查找买家秀图片
		 */
		public List<Map<String,Object>> queryPhoto(String mno){
			String sql="select * from discuss where mno=?";
			return new DBHelper().query(sql,mno);
			
		}
		//查询评论
		public List<OrderItemInfo> queryGno1(String mno,String gno){
			String sql="select * from discuss where mno=? and gno=?";
			return new DBHelper().query(sql, OrderItemInfo.class,gno);
			
		}
		/**
		 * 发表评论
		 */
		
		public void insertDiscuss(String mno,String nickName,String content,String photo,String gno,String picture1,String picture2,String picture3) {
			String sql="insert into discuss values(?,?,?,?,now(),?,?,?,?)";
			new DBHelper().update(sql, mno,nickName,content,photo,gno,picture1,picture2,picture3);
		}
		
		/**
		 * 查商品gno
		 * @return 
		 */
		public List<OrderItemInfo> queryGno(String ono){
			String sql="select gno from orderiteminfo where ono=?";
			return new DBHelper().query(sql, OrderItemInfo.class,ono);
			
		}
		
		//更新商品状态为一评论的的状态变为4
				public int updateStatus4(String gno) {
					String sql = "UPDATE orderinfo o1,orderiteminfo o2 SET o1.`status`=4,o2.`status`=4 WHERE o1.ono=o2.ono AND o2.gno=?";
					return new DBHelper().update(sql, gno);
				}
}
