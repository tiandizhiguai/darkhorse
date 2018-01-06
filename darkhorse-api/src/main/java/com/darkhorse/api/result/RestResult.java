package com.darkhorse.api.result;

import java.io.Serializable;

public class RestResult<D extends Serializable> implements Result<D> {

	private int code = 0;

	private String msg;

	private D data;

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

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}

}
