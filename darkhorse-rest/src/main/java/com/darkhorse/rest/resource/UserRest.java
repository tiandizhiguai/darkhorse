package com.darkhorse.rest.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRest extends AbstractRest {

	@Bean
	public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
		return RouterFunctions.route(RequestPredicates.GET("/user/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::get)
				.andRoute(RequestPredicates.GET("/user/page/{pageNum}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::page)
				.andRoute(RequestPredicates.POST("/user").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::update);
	}
}