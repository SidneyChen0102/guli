package com.sidney.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Sidney
 * @data 2020/5/1  23:37
 * @description
 */

@SpringBootApplication
@EnableDiscoveryClient //nacos注册
@EnableFeignClients
@ComponentScan(basePackages = {"com.sidney"})
public class EduApplication {
    public static void main(String[] args) {
      SpringApplication.run(EduApplication.class,args);

    }
}
