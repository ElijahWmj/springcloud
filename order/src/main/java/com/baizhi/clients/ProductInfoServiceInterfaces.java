package com.baizhi.clients;

import com.baizhi.dto.StockDTO;
import com.baizhi.entity.ProductInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PRODUCT")
public interface ProductInfoServiceInterfaces {

    @RequestMapping("/product/findById")
    public ProductInfo getProductInfo(@RequestParam("id") String id);


    @RequestMapping("/product/stock")
    public void updateStock(@RequestBody StockDTO stockDTO);


}
