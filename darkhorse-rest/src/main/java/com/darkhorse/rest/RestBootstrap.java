package com.darkhorse.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 引导服务入口。
 * 
 * @author hzcgw
 *
 */
@SpringBootApplication(scanBasePackages= {"com.darkhorse.service", "com.darkhorse.rest"})
public class RestBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(RestBootstrap.class, args);
	}
}