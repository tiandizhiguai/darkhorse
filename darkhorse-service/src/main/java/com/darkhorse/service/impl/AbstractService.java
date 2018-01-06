package com.darkhorse.service.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.darkhorse.api.param.PageParam;
import com.darkhorse.api.result.PageData;
import com.darkhorse.api.result.ServiceResult;
import com.darkhorse.api.service.Service;
import com.darkhorse.service.common.ClassUtils;
import com.darkhorse.service.dao.Dao;
import com.darkhorse.service.dao.entity.AbstractEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class AbstractService<M extends Serializable, P extends PageParam> implements Service<M, P> {

	@Override
	public ServiceResult<List<M>> getList(P param) {
		List<?> entitys = getDao().selectList(param);
		if (CollectionUtils.isEmpty(entitys)) {
			return null;
		}
		List<M> models = entitys.stream().map(e -> {
			return resolveModelData(e);
		}).collect(Collectors.toList());

		return result(models);
	}

	@Override
	public ServiceResult<PageData<M>> getPage(P param) {
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<?> entitys = getDao().selectList(param);
		PageInfo<?> orgPage = new PageInfo<>(entitys);
		orgPage.setList(null);
		
		List<M> models = entitys.stream().map(e -> {
			return resolveModelData(e);
		}).collect(Collectors.toList());

		PageData<M> targetPage = new PageData<>();
		BeanUtils.copyProperties(orgPage, targetPage);
		targetPage.setList(models);

		return result(targetPage);
	}

	@Override
	public ServiceResult<M> get(P param) {
		Object entity = getDao().select(param);
		return result(resolveModelData(entity));
	}

	private M resolveModelData(Object e) {
		M model = ClassUtils.newFirstParameterizedInstance(this.getClass().getGenericSuperclass());
		BeanUtils.copyProperties(e, model);
		return model;
	}

	@Override
	public ServiceResult<Long> insert(P param) {
		Object entity = resolveEntityData(param);
		getDao().insert(entity);
		if (entity instanceof AbstractEntity) {
			return result(((AbstractEntity) entity).getId());
		}
		return result(-1L);
	}

	@Override
	public ServiceResult<Integer> update(P param) {
		return result(getDao().update(param));
	}

	private Object resolveEntityData(P param) {
		Dao<?, P> dao = getDao();
		Class<?> daoInterface = dao.getClass().getInterfaces()[0];
		Type type = daoInterface.getGenericInterfaces()[0];
		Object entity = ClassUtils.newFirstParameterizedInstance(type);
		BeanUtils.copyProperties(param, entity);
		return entity;
	}

	@Override
	public ServiceResult<Integer> delete(Long id) {
		return result(getDao().delete(id));
	}

	private <D> ServiceResult<D> result(D data) {
		return new ServiceResult<D>(data);
	}

	protected abstract <E> Dao<E, P> getDao();
}