package com.darkhorse.api.result;

public interface Result<D> {

	void setData(D data);

	D getData();
}
