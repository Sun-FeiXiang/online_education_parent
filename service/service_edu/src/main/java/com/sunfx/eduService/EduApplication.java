package com.sunfx.eduService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Feixiang Sun
 * @date 2021/8/17 15:45
 */
@SpringBootApplication
@EnableDiscoveryClient //Nacos注册
@ComponentScan(basePackages = {"com.sunfx"})//扫描规则
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
