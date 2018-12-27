package com.baizhi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CartItemVO {

    @JsonProperty("productId")
    private String id;

    private Integer productQuantity;

}
