package com.darkhorse.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.darkhorse.api.param.UsersParam;
import com.darkhorse.api.service.UsersService;

import reactor.core.publisher.Mono;

@Configuration
public class UserRest extends AbstractRest {

	@Autowired
	private UsersService usersService;

	@Bean
	public RouterFunction<ServerResponse> route() {
		return RouterFunctions.route(get("/user/{id}"), request -> {
			UsersParam param = new UsersParam();
			param.setId(Long.valueOf(request.pathVariable("id")));
			return result(Mono.just(usersService.get(param)));
		})
		.andRoute(get("/user/page/{pageNum}"), request -> {
			UsersParam param = new UsersParam();
			param.setPageNum(Integer.valueOf(request.pathVariable("pageNum")));
			return result(Mono.just(usersService.getPage(param)));
		})
		.andRoute(post("/user"), request -> {
			Mono<UsersParam> user = request.bodyToMono(UsersParam.class);
			return result(Mono.just(usersService.insert(user.block())));
		})
		.andRoute(put("/user"), request -> {
			Mono<UsersParam> user = request.bodyToMono(UsersParam.class);
			return result(Mono.just(usersService.update(user.block())));
		});
	}
}