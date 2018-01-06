package com.darkhorse.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.darkhorse.api.param.UsersParam;
import com.darkhorse.api.result.ServiceResult;
import com.darkhorse.api.service.UsersService;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {

	@Autowired
	private UsersService usersService;

	public Mono<ServerResponse> get(ServerRequest request) {
		UsersParam param = new UsersParam();
		param.setId(Long.valueOf(request.pathVariable("id")));
		return ServerResponse.ok().body(Mono.just(usersService.get(param)), ServiceResult.class);
	}

	public Mono<ServerResponse> page(ServerRequest request) {
		UsersParam param = new UsersParam();
		param.setPageNum(Integer.valueOf(request.pathVariable("pageNum")));
		return ServerResponse.ok().body(Mono.just(usersService.getPage(param)), ServiceResult.class);
	}

	public Mono<ServerResponse> update(ServerRequest request) {
		Mono<UsersParam> user = request.bodyToMono(UsersParam.class);
		usersService.insert(user.block());
		return null;
	}
}