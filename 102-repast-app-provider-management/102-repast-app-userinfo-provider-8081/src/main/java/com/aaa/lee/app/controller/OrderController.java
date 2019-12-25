package com.aaa.lee.app.controller;

import com.aaa.lee.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 查询我的订单
     * @param token
     * @return
     */
    @PostMapping("/selectOrder")
    public Map<String, Object> selectOrder(@RequestParam("token")String token) {
        return orderService.selectOrder(token);
        //return orderService.selectTestOrder(token);
    }

    /**
     * 查看订单的详情
     * @param token
     * @param orderSn
     * @return
     */
    @PostMapping("/selectOrderDefatl")
    public Map<String ,Object> selectOrderDefatl(@RequestParam("token")String token,@RequestParam("orderSn")String orderSn){
     return orderService.selectOrderDefatl(token,orderSn);
    }
}
