package com.baizhi.interfaces;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "HELLO-SERVICE",fallback = HelloServiceHystrixImpl.class) //明确该接口对应eureka中的哪个服务
public interface HelloServiceInterface {

    @RequestMapping("/hello/hello/")
    public String hello(@RequestParam("name") String bb);


}
