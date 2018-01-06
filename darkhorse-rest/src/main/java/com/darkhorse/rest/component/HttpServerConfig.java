package com.darkhorse.rest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import com.darkhorse.rest.constants.ConfigConstants;

import reactor.ipc.netty.http.server.HttpServer;

@Configuration
public class HttpServerConfig {

	@Autowired
	private ConfigConstants configConstants;

	@Bean
	public HttpServer httpServer(RouterFunction<?> routerFunction) {
		HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction);
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
		HttpServer server = HttpServer.create("localhost", configConstants.getServerPort());
		server.newHandler(adapter);
		return server;
	}
}