package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: 102-repast-app-parent
 * @description
 * @author: sjx
 * @create: 2019-12-19 22:18
 **/
@RestController
@Api(value = "优惠券信息", tags = "优惠券接口")
public class CouponController extends BaseController {

    @Autowired
    private IRepastService repastService;


    /**
     *执行查询优惠券操作
     * @param token
     * @return
     */
    @PostMapping("/selectCoupon")
    @ApiOperation(value = "优惠券", notes = "查询优惠券操作")
    public ResultData selectCoupon(String token) {
        if(repastService.selectCoupon(token)!=null) {
            // 登录成功
            return success();
        }
        return failed();
    }
}
