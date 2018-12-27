package com.baizhi.interfaces;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceHystrixImpl  implements  HelloServiceInterface{

    @Override
    public String hello(String bb) {
        return "servers is down, name: "+bb;
    }
}
