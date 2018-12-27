package com.baizhi.controller;

import com.baizhi.dto.StockDTO;
import com.baizhi.entity.ProductInfo;
import com.baizhi.service.ProductInfoService;
import com.baizhi.vo.CartItemVO;
import com.baizhi.vo.OrderVO;
import com.baizhi.vo.ResultVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductInfoController {


    @Autowired
    private ProductInfoService productInfoService;


    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/list")
    public @ResponseBody ResultVO findAll(){
        ResultVO resultVO = productInfoService.findProductInfos();
        return resultVO;
    }


    @RequestMapping("/show")
    public String show(Model model){
        List<ProductInfo> productInfos = productInfoService.findAll();
        model.addAttribute("productInfos",productInfos);
        return "show";
    }


    @RequestMapping("/add")
    public String addCart(String id, HttpSession session){
        Map<String,Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        if(cart==null){
            cart = new HashMap<String,Integer>();
            session.setAttribute("cart",cart);
        }

        //处理购物车添加
        if(cart.containsKey(id)){
            cart.put(id,(cart.get(id)+1));
        }else{
            cart.put(id,1);
        }
        return "redirect:/cart.jsp";
    }



    @RequestMapping("/order")
    public @ResponseBody  String addOrder(HttpSession session){

        //获取购物车中数据
        Map<String,Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

        //创建订单VO
        OrderVO orderVO = new OrderVO();
        orderVO.setName("小陈");
        orderVO.setAddress("北京备课职业");
        orderVO.setPhone("xxxxx");
        orderVO.setOpenId("123");

        List<CartItemVO> cartItemVOS = new ArrayList<CartItemVO>();
        //创建商品项VO
        for (Map.Entry<String,Integer> car : cart.entrySet()) {
            CartItemVO cartItemVO = new CartItemVO();
            cartItemVO.setId(car.getKey());
            cartItemVO.setProductQuantity(car.getValue());
           cartItemVOS.add(cartItemVO);
        }

        orderVO.setItems(cartItemVOS);

        //调用订单服务 生成订单

        String s = restTemplate.postForObject("http://ORDER/order/create", orderVO, String.class);

        return s;
    }



    @RequestMapping(value = "/findById")
    public @ResponseBody ProductInfo getProductInfo(String id){
        return productInfoService.findById(id);
    }


    @RequestMapping("/stock")
    public @ResponseBody void updateStock(@RequestBody StockDTO stockDTO){
        productInfoService.updateStock(stockDTO);
    }


}
