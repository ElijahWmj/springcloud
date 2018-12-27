package com.baizhi.controller;


import com.baizhi.interfaces.HelloServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowHelloController {



    @Autowired
    private HelloServiceInterface helloServiceInterface;


    @RequestMapping("/hello")
    public  String hello(String name){
        String hello = helloServiceInterface.hello(name);
        return hello;
    }



}
