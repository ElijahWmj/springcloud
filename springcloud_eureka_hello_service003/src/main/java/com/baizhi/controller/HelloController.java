package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hello")
    public String helloName(String name){
        System.out.println("姓名:  "+name);
        return "server3---hello: "+name;
    }

}
