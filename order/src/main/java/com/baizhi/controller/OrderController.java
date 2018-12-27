package com.baizhi.controller;

import com.baizhi.service.OrderService;
import com.baizhi.vo.OrderVO;
import com.baizhi.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create")//DTO  Data Transfer Object
    public ResultVO create(@RequestBody  OrderVO orderVO){
        System.out.println(orderVO);
        String orderId = orderService.createOrder(orderVO);
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("提交订单成功");
        resultVO.setCode(0);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("orderId",orderId);
        resultVO.setData(data);
        return resultVO;
    }



}
