package com.ramble.ramblegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@EnableDiscoveryClient
@RefreshScope
@SpringBootApplication
public class RambleGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(RambleGatewayApp.class, args);
    }

}
