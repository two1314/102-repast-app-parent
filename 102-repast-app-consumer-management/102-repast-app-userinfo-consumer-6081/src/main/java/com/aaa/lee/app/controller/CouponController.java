package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.status.StatusEnum;
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
            // 查询成功
            return success(repastService.selectCoupon(token), StatusEnum.SUCCESS.getMsg());
        }
        return failed();
    }
    /**
     *执行修改优惠券操作
     * @param token
     * @return
     */
    @PostMapping("/updateCoupon")
    @ApiOperation(value = "优惠券", notes = "修改优惠券操作")
    public ResultData updateCoupon(Integer useCount, Long couponId,String token) {
        if(repastService.updateCoupon(useCount,couponId,token)!=null) {
            // 查询成功
            return success(repastService.updateCoupon(useCount,couponId,token), StatusEnum.SUCCESS.getMsg());
        }
        return failed(StatusEnum.FAILED.getMsg());
    }
}
