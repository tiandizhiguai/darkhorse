package com.darkhorse.rest.resource;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.darkhorse.api.result.RestResult;
import com.darkhorse.api.result.ServiceResult;

import reactor.core.publisher.Mono;

public abstract class AbstractRest {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

//	<D extends Serializable> Mono<ServerResponse> successResult(D data) {
//		return result(0, null, data);
//	}
//
//	<D extends Serializable> Mono<ServerResponse> failResult(String msg) {
//		return result(-1, msg, null);
//	}
//
//	<D extends Serializable> Mono<ServerResponse> failResult(String msg, D data) {
//		return result(-1, msg, data);
//	}

	<D extends Serializable> Mono<ServerResponse> result(Mono<ServiceResult<D>> serviceMonoData) {
		ServiceResult<D> serviceResult = serviceMonoData.block();
		RestResult<D> restResult = new RestResult<>();
		restResult.setCode(serviceResult.getCode());
		restResult.setData(serviceResult.getData());
		restResult.setMsg(serviceResult.getMsg());
		return ServerResponse.ok().body(Mono.just(restResult), RestResult.class);
	}

	RequestPredicate get(String path) {
		return RequestPredicates.GET(path).and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
	}
	
	RequestPredicate post(String path) {
		return RequestPredicates.POST(path).and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
	}
	
	RequestPredicate put(String path) {
		return RequestPredicates.PUT(path).and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
	}
	
	RequestPredicate delete(String path) {
		return RequestPredicates.DELETE(path).and(RequestPredicates.accept(MediaType.APPLICATION_JSON));
	}
}
