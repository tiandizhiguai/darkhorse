package com.darkhorse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darkhorse.api.Users;
import com.darkhorse.api.exception.ServiceException;
import com.darkhorse.api.param.UsersParam;
import com.darkhorse.api.result.ServiceResult;
import com.darkhorse.api.service.UsersService;
import com.darkhorse.service.dao.Dao;
import com.darkhorse.service.dao.UsersDao;

@Service("usersService")
public class UsersServiceImpl extends AbstractService<Users, UsersParam> implements UsersService {

	@Autowired
	private UsersDao userDao;
	
	@Override
	@SuppressWarnings("unchecked")
	protected Dao<?, UsersParam> getDao() {
		return this.userDao;
	}

	@Transactional
	@Override
	public ServiceResult<Users> login(UsersParam param) throws ServiceException {
		return this.get(param);
	}

}