package com.baizhi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {

    private String name;
    private String type;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOS;



}
