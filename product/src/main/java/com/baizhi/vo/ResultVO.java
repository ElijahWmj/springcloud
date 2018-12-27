package com.baizhi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultVO {

    private Integer code;
    private String msg;

    @JsonProperty("data")
    private List<ProductCategoryVO> productCategoryVOS;



}
