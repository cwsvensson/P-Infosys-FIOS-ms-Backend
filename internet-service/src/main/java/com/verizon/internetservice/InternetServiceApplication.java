package com.verizon.internetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class InternetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetServiceApplication.class, args);

	}

}
