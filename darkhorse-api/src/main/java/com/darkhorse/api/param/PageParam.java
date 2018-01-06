package com.darkhorse.api.param;

import java.io.Serializable;

public abstract class PageParam implements Param, Serializable {

	private static final long serialVersionUID = -1550086740215182186L;

	private int pageSize = 10;

	private int pageNum;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}