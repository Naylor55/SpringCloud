package com.ramble.carservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project     car-service
 * Package     com.ramble.carservice.controller
 * Class       HelloController
 * Date        2022/10/26 10:28
 * Author      MingliangChen
 * Email       cnaylor@163.com
 * Description
 */

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/world")
    public String sayHello() {
        return "Hello";
    }
}
