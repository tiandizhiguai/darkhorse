package com.darkhorse.rest.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigConstants {

	@Value("${server.port}")
	private Integer serverPort;

	public Integer getServerPort() {
		return serverPort;
	}
}
