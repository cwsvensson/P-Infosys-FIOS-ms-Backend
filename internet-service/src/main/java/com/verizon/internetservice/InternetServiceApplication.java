package com.verizon.internetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
public class InternetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetServiceApplication.class, args);
	}

}
