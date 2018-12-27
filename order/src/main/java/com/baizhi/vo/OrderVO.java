package com.baizhi.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderVO {

    private String name;
    private String phone;
    private String address;
    private String openId;

    List<CartItemVO> items;




}
