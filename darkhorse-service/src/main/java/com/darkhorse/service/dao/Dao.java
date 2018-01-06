package com.darkhorse.service.dao;

import java.util.List;

import com.darkhorse.api.param.Param;

public interface Dao<E, P extends Param> {

	List<E> selectList(P param);

	E select(P param);

	Long insert(E e);

	Integer update(E e);

	Integer delete(Long id);

}
