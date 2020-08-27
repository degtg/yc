package com.yc.spring.bank;

public class Account implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer acccountid;
	private String password;
	private Double balance;
	public Integer getAcccountid() {
		return acccountid;
	}
	public void setAcccountid(Integer acccountid) {
		this.acccountid = acccountid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
}
