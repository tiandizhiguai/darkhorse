package com.darkhorse.rest.resource;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkhorse.api.result.RestResult;
import com.darkhorse.api.result.Result;

public abstract class AbstractRest {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	<D extends Serializable> Result<D> successResult(D data) {
		return result(0, null, data);
	}

	<D extends Serializable> Result<D> failResult(String msg) {
		return result(-1, msg, null);
	}

	<D extends Serializable> Result<D> failResult(String msg, D data) {
		return result(-1, msg, data);
	}

	<D extends Serializable> Result<D> result(int code, String msg, D data) {
		Result<D> result = new RestResult<>();
		result.setData(data);
		return result;
	}
}
