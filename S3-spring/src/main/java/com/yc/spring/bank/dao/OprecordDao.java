package com.yc.spring.bank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.spring.bank.Account;
import com.yc.spring.bank.Oprecord;
@Repository
public class OprecordDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;//DBHelper
	Account account=new Account();
	
	public void insert(int id,double money) {
		jdbcTemplate.update("insert into oprecord values(null,?,?,null,now())",id,money);
	}
	
	
	public List<Oprecord> selectById(int accountid) {
		String sql="select * from oprecord where accountid=?";
		Object[] args= {accountid};
		//ResultSetExtractor 是回调接口，用于结果集中的数据转换成功Account对象
		return jdbcTemplate.query(sql, args,new RowMapper<Oprecord>() {

			public Oprecord mapRow(ResultSet rs, int rowNum) throws SQLException {
				Oprecord o=new Oprecord();
				o.setId(rs.getInt("id"));
				o.setAccountid(rs.getInt("accountid"));
				o.setOpmoney(rs.getDouble("opmoney"));
				o.setCharge(rs.getDouble("charge"));
				o.setOptime(rs.getTimestamp("optime"));
				return o;
			}
			
		});
		
		
	}
}
