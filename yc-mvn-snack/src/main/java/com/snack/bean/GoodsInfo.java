package com.snack.bean;

public class GoodsInfo {

	private int gno;
	private String gname;
	private int tno;
	private double price;
	private String intro;
	private int balance;
	private String pics;
	private String unit;
	private String qperied;
	private String weight;
	private String descr;
	private int status;
	
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getQperied() {
		return qperied;
	}
	public void setQperied(String qperied) {
		this.qperied = qperied;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gno;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GoodsInfo other = (GoodsInfo) obj;
		if (gno != other.gno)
			return false;
		return true;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		status = status;
	}
	@Override
	public String toString() {
		return "GoodsInfo [gno=" + gno + ", gname=" + gname + ", tno=" + tno + ", price=" + price + ", intro=" + intro
				+ ", balance=" + balance + ", pics=" + pics + ", unit=" + unit + ", qperied=" + qperied + ", weight="
				+ weight + ", descr=" + descr + ", Status=" + status + "]";
	}
	
	
	
}
