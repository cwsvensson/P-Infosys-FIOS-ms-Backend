package com.verizon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FiosServiceApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(FiosServiceApplication.class, args);
	}
}
