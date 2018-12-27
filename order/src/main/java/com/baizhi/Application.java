package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;



@MapperScan("com.baizhi.dao")
@SpringCloudApplication
@EnableFeignClients
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

    }


}
