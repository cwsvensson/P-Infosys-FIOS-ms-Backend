package com.verizon.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class IEnServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(IEnServiceRegistryApplication.class, args);
	}

}
