package com.snack.bean;

public class GoodsType {

	private int tno;
	private String tname;
	private int status;
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
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
		result = prime * result + tno;
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
		GoodsType other = (GoodsType) obj;
		if (tno != other.tno)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GoodsType [tno=" + tno + ", tname=" + tname + ", status=" + status + "]";
	}
	
	
}
