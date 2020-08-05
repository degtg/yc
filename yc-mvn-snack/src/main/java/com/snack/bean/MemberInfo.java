package com.snack.bean;

import java.sql.Date;

public class MemberInfo {

	private int mno;
	private String nickName;
	private String realName;
	private String pwd;
	private String tel;
	private String email;
	private String photo;
	private Date regDate;
	private String status;
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mno;
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
		MemberInfo other = (MemberInfo) obj;
		if (mno != other.mno)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MemberInfo [mno=" + mno + ", nickName=" + nickName + ", realName=" + realName + ", pwd=" + pwd
				+ ", tel=" + tel + ", email=" + email + ", photo=" + photo + ", regDate=" + regDate + ", status="
				+ status + "]";
	}
	
	
	
}
