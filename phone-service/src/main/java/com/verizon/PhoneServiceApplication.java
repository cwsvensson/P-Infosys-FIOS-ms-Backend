package com.verizon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhoneServiceApplication {

    public static void main(String[] args) { SpringApplication.run(PhoneServiceApplication.class, args);
    }

}
