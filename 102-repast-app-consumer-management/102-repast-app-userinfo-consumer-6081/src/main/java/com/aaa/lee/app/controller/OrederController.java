package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.status.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(value = "我的订单",tags ="我的订单接口" )
public class OrederController extends BaseController {
    @Autowired
    private IRepastService repastService;

    /**
     * 查询我的订单
     * @param token
     * @return
     */
    @PostMapping("/selectOrder")
    @ApiOperation(value = "我的订单",notes ="我的订单")
    public ResultData selectOrder(String token){
        if (null !=token){
            Map<String, Object> stringObjectMap = repastService.selectOrder(token);
            if (null != stringObjectMap){
               return success(stringObjectMap, StatusEnum.SUCCESS.getMsg());
           }
        }
        return failed();
    }

    /**
     * 查看订单的详情
     * @param token
     * @param orderSn
     * @return
     */
    @PostMapping("/selectOrderDefatl")
    @ApiOperation(value = "订单详情",notes ="我的订单数据")
    public ResultData selectOrderDefatl(String token,String orderSn){
        if (null !=token){
            if (null != repastService.selectOrderDefatl(token,orderSn)){
                return success(repastService.selectOrderDefatl(token,orderSn), StatusEnum.SUCCESS.getMsg());
            }
        }
        return failed();
    }
}
