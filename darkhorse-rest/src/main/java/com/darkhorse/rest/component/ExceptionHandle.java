package com.darkhorse.rest.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.darkhorse.api.result.RestResult;
import com.darkhorse.api.result.Result;

/**
 * 
* 统一异常处理
* 
* @version V1.0
* @author cgw
* @Date 2017年9月4日 下午5:58:33
* @since JDK 1.8
 */
@ControllerAdvice
public class ExceptionHandle {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
	
	@ResponseBody
	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<Result<String>> handle(Throwable e) {
		logger.error("server error.", e);
		RestResult<String> result = new RestResult<>();
		result.setData("服务器发生错误");
		result.setCode(-1);
		return new ResponseEntity<Result<String>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
