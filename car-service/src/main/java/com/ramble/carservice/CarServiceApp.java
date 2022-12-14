package com.ramble.carservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class CarServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApp.class, args);
    }

}
