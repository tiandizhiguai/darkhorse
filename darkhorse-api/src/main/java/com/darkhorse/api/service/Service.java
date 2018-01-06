package com.darkhorse.api.service;

import java.io.Serializable;
import java.util.List;

import com.darkhorse.api.param.PageParam;
import com.darkhorse.api.result.PageData;
import com.darkhorse.api.result.ServiceResult;

public interface Service<M extends Serializable, P extends PageParam> {

	ServiceResult<List<M>> getList(P param);
	
	ServiceResult<PageData<M>> getPage(P param);

	ServiceResult<M> get(P param);

	ServiceResult<Long> insert(P param);

	ServiceResult<Integer> update(P param);

	ServiceResult<Integer> delete(Long id);

}
