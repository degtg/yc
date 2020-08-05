package com.snack.bean;

import java.util.List;

public class OrderItemInfo {

	private int ino;
	private String ono;
	public List<GoodsInfo> getGoodslist() {
		return goodslist;
	}
	public void setGoodslist(List<GoodsInfo> goodslist) {
		this.goodslist = goodslist;
	}
	private int gno;
	private int nums;
	private double price;
	private int status;
	
	private List<GoodsInfo> goodslist;
	
	
	
	//订单明细记录的商品信息的对象
	private GoodsInfo goodsInfo;
	
	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	public String getOno() {
		return ono;
	}
	public void setOno(String ono) {
		this.ono = ono;
	}
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ino;
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
		OrderItemInfo other = (OrderItemInfo) obj;
		if (ino != other.ino)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderItemInfo [ino=" + ino + ", ono=" + ono + ", gno=" + gno + ", nums=" + nums + ", price=" + price
				+ ", status=" + status + "]";
	}
	
	
}
