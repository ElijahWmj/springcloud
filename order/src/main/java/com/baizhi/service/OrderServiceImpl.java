package com.baizhi.service;

import com.baizhi.clients.ProductInfoServiceInterfaces;
import com.baizhi.dao.OrderDetailMapper;
import com.baizhi.dao.OrderMasterMapper;
import com.baizhi.dto.StockDTO;
import com.baizhi.entity.OrderDetail;
import com.baizhi.entity.OrderMaster;
import com.baizhi.entity.ProductInfo;
import com.baizhi.vo.CartItemVO;
import com.baizhi.vo.OrderVO;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductInfoServiceInterfaces productInfoServiceInterfaces;


    @Override
    public String createOrder(OrderVO orderVO) {

        String orderId = UUID.randomUUID().toString();
        //生成订单
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerName(orderVO.getName());
        orderMaster.setBuyerAddress(orderVO.getAddress());
        orderMaster.setBuyerOpenid(orderVO.getOpenId());
        orderMaster.setBuyerIphone(orderVO.getPhone());
        orderMaster.setCreateTime(new Date());
        //设置订单中商品数量

        BigDecimal bigDecimal = new BigDecimal(0);
        List<CartItemVO> items = orderVO.getItems();
        for (CartItemVO item : items) {
            ProductInfo productInfo = productInfoServiceInterfaces.getProductInfo(item.getId());
            BigDecimal multiply = new BigDecimal(item.getProductQuantity()).multiply(productInfo.getProductPrice());
            bigDecimal =  bigDecimal.add(multiply);

        }

        orderMaster.setOrderAmount(bigDecimal);//计算总金额
        orderMaster.setOrderStatus(Byte.valueOf("1"));
        orderMaster.setPayStayus(Byte.valueOf("0"));
        orderMaster.setUpdateTime(new Date());
        orderMaster.setOrderId(orderId);
        orderMasterMapper.insert(orderMaster);


        //创建扣存库的map集合
        Map<String,Integer> stocks = new HashMap<String,Integer>();
        //创建订单项
        List<CartItemVO> items1 = orderVO.getItems();
        for (CartItemVO cartItemVO : items1) {
            //创建订单详细
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(UUID.randomUUID().toString());
            orderDetail.setCreateTime(new Date());
            orderDetail.setOrderId(orderId);
            orderDetail.setUpdateTime(new Date());
            orderDetail.setProductQuantity(cartItemVO.getProductQuantity());
            //订单详细中的商品信息
            ProductInfo productInfo = productInfoServiceInterfaces.getProductInfo(cartItemVO.getId());
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailMapper.insert(orderDetail);
            stocks.put(cartItemVO.getId(),cartItemVO.getProductQuantity());
        }


        //扣库存
        StockDTO stockDTO = new StockDTO();
        stockDTO.setStocks(stocks);

        productInfoServiceInterfaces.updateStock(stockDTO);

        return orderId;

    }
}
