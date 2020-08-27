package com.yc.LxBlog.bean;

public class Result {

	private int code; //0失败 1成功
	private String msg;//消息
	private Object data;//返回的数据
	
	public Result(int code) {
		super();
		this.code = code;
		
	}
	
	public Result(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
		
	}
	public Result(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Result(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
