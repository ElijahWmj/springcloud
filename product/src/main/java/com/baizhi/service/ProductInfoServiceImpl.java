package com.baizhi.service;

import com.baizhi.dao.ProductCategoryMapper;
import com.baizhi.dao.ProductInfoMapper;
import com.baizhi.dto.StockDTO;
import com.baizhi.entity.ProductCategory;
import com.baizhi.entity.ProductCategoryExample;
import com.baizhi.entity.ProductInfo;
import com.baizhi.entity.ProductInfoExample;
import com.baizhi.vo.ProductCategoryVO;
import com.baizhi.vo.ProductInfoVO;
import com.baizhi.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductInfoServiceImpl implements ProductInfoService {



    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;




    @Override
    public void updateStock(StockDTO stockDTO) {
        Map<String, Integer> stocks = stockDTO.getStocks();
        for (Map.Entry<String,Integer> stock : stocks.entrySet()) {
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(stock.getKey());
            productInfo.setProductStock(productInfo.getProductStock()- stock.getValue());
            productInfoMapper.updateByPrimaryKey(productInfo);
        }

    }







    @Override
    public ProductInfo findById(String id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }




    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO findProductInfos() {

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("查询成功");

        //创建类别集合
        List<ProductCategoryVO> productCategoryVOS = new ArrayList<ProductCategoryVO>();

        //查询所有类别对象
        List<ProductCategory> productCategories = productCategoryMapper.selectByExample(new ProductCategoryExample());
        for (ProductCategory productCategory : productCategories) {
            //创建类别信息
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();



            productCategoryVO.setName(productCategory.getCategoryName());
            productCategoryVO.setType(productCategory.getCategoryType()+"");



            //根据商品类型id查询所有商品信息
            ProductInfoExample example = new ProductInfoExample();
            example.createCriteria().andCategoryTypeEqualTo(productCategory.getCategoryType());
            List<ProductInfo> productInfos = productInfoMapper.selectByExample(example);


            List<ProductInfoVO> productInfoVOS = new ArrayList<ProductInfoVO>();

            for (ProductInfo productInfo : productInfos) {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo,productInfoVO);//属性一致  类型 名称一致
                productInfoVOS.add(productInfoVO);
            }


            //设置类别中的商品信息
            productCategoryVO.setProductInfoVOS(productInfoVOS);

            //保存多个类别信息
            productCategoryVOS.add(productCategoryVO);
        }

        //维护结果和类别的关系
        resultVO.setProductCategoryVOS(productCategoryVOS);


        return resultVO;
    }
}
