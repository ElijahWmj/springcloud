package com.baizhi.service;

import com.baizhi.dto.StockDTO;
import com.baizhi.entity.ProductInfo;
import com.baizhi.vo.ResultVO;

import java.util.List;

public interface ProductInfoService {




    public ResultVO findProductInfos();


    public List<ProductInfo> findAll();

    public ProductInfo findById(String id);

    public void updateStock(StockDTO stockDTO);  //商品id -----> 商品数量


}
