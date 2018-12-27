package com.baizhi.controller;

import com.netflix.discovery.converters.Auto;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/show")
public class ShowHelloController {


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String showHello(String name){

        //IRule iRule;

        //loadBalancerClient.choose("HELLO-SERVICE");



        //第一方式调用
        /*RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://localhost:8989/hello/hello?name=" + name, String.class);
        System.out.println(forObject);*/

        //第二种方式调用  //用来书写调用服务名称
        /*ServiceInstance serviceInstance = loadBalancerClient.choose("HELLO-SERVICE");
        System.out.println(serviceInstance.getHost());
        System.out.println(serviceInstance.getPort());

        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hello/hello?name=" + name,
                String.class);*/

        //第三种方式
        String forObject = restTemplate.getForObject("http://hello-service/hello/hello?name=" + name, String.class);

        return forObject;

    }



}
