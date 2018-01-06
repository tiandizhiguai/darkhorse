package com.darkhorse.api.result;

import java.io.Serializable;

public class ServiceResult<D> implements Result<D>, Serializable {

	private static final long serialVersionUID = 3721565338785217309L;

	public int code = 0x0000;

	public String msg;

	private D data;
	
	public ServiceResult() {
		
	}
	
	public ServiceResult(D data) {
		this.data = data;
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

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}
}
