package com.baizhi.vo;

import lombok.Data;

import java.util.Map;

@Data
public class ResultVO {

    private Integer code;
    private String msg;
    private Map<String,String> data;


}
