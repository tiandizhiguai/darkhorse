package com.darkhorse.api.service;

import com.darkhorse.api.Users;
import com.darkhorse.api.exception.ServiceException;
import com.darkhorse.api.param.UsersParam;
import com.darkhorse.api.result.ServiceResult;

public interface UsersService extends Service<Users, UsersParam> {

	ServiceResult<Users> login(UsersParam param) throws ServiceException;

}