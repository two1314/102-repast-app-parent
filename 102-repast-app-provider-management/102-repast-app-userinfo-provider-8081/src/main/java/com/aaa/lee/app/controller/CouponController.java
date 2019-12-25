package com.aaa.lee.app.controller;

import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: 102-repast-app-parent
 * @description
 * @author: sjx
 * @create: 2019-12-19 20:20
 **/
@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;

    /**
     *查询用户拥有的优惠券
     * @param token
     * @return
     */
    @PostMapping("/selectCoupon")
    public List<Coupon> selectCoupon(@RequestParam("token")String token) {
        // 调用service
        return couponService.selectCoupon(token);
    }

    /**
     *优惠用户的优惠券
     * @param useCount
     * @param id
     * @param token
     * @return
     */
    @PostMapping("/updateCoupon")
    public Map<String, Object> updateCoupon(@RequestParam("useCount")Integer useCount, @RequestParam("id") Long id, @RequestParam("token")String token) {
        // 调用service
        return couponService.updateCoupon(useCount,id,token);
    }
}