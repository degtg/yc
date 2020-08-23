package com.yc.spring.bank;

import java.sql.Date;
import java.sql.Timestamp;

public class Oprecord implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6026532148170395664L;
	private Integer id;
	private Integer accountid;
	private double opmoney;
	private double charge;
	private Timestamp optime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	public double getOpmoney() {
		return opmoney;
	}
	public void setOpmoney(double opmoney) {
		this.opmoney = opmoney;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public Timestamp getOptime() {
		return optime;
	}
	public void setOptime(Timestamp optime) {
		this.optime = optime;
	}

	
	
}
