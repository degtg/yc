package com.alipay.config;

import java.util.List;
import java.util.Map;

import com.snack.util.DBHelper;

public class PayDao {
	// 查询用户某次订单详细
	public List<Map<String, Object>> query(String ono) {
		String sql = "SELECT SUM(a.nums)totalnum,SUM(a.nums*a.price)totalprice "
				+ "FROM orderiteminfo a JOIN goodsinfo b ON a.gno = b.gno " 
				+ "WHERE ono =? ";
		return new DBHelper().query(sql, ono);
	}

	// 查询订单中心未支付的订单
	public List<Map<String, Object>> query1(String mno) {
		String sql = "SELECT a.ono,a.odate,a.price " 
					+ "FROM orderinfo a JOIN addrinfo b"
					+ " ON a.ano = b.ano WHERE b.mno=? " 
					+ "AND a. STATUS = 0 ORDER BY ono DESC "
		+ "LIMIT 0, 1 ";
		return new DBHelper().query(sql, mno);
	}

	// 查询订单中心已支付的订单
	public List<Map<String, Object>> queryPay(String mno) {
		String sql = "SELECT a.ono,a.odate,a.price " + "FROM orderinfo a JOIN addrinfo b"
				+ " ON a.ano = b.ano WHERE b.mno=? " + "AND a. STATUS = 1 ORDER BY ono DESC ";
		return new DBHelper().query(sql, mno);
	}

	//更新支付状态为已支付的状态变为1
	public int updatePay(String ono) {
		String sql = "UPDATE orderinfo set status=1 where ono=?";
		return new DBHelper().update(sql, ono);
	}
	// 查询订单中心待收货的订单
		public List<Map<String, Object>> querywait(String mno) {
			String sql = "SELECT a.ono,a.odate,a.price " + "FROM orderinfo a JOIN addrinfo b"
					+ " ON a.ano = b.ano WHERE b.mno=? " + "AND a. STATUS = 2 ORDER BY ono DESC ";
			return new DBHelper().query(sql, mno);
		}
		// 查询订单中心待评论的订单
				public List<Map<String, Object>> querywaitComment(String mno) {
					String sql = "SELECT a.ono,a.odate,a.price " + "FROM orderinfo a JOIN addrinfo b"
							+ " ON a.ano = b.ano WHERE b.mno=? " + "AND a. STATUS = 3 ORDER BY ono DESC ";
					return new DBHelper().query(sql, mno);
				}

}
