package com.aaa.lee.app.api;

import com.aaa.lee.app.fallback.RepastFallback;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/12/19 14:55
 * @Description
 *      如果是简单类型传递数据可以传递多个，但是每一个必须要用@RequestParam
 *      但是如果是包装类型，只能传递一个，也必须要用@RequestBody
 **/
@FeignClient(value = "userinfo-interface-provider", fallbackFactory = RepastFallback.class)
public interface IRepastService {

    /**
     * @author Seven Lee
     * @description
     *      登录熔断数据
     * @param member
     * @date 2019/12/19
     * @return java.lang.Boolean
     * @throws 
    **/
    @PostMapping("/doLogin")
    String doLogin(@RequestBody Member member);

    /**
     * 通过token查询会员熔断
     * @param token
     * @return
     */
    @PostMapping("/selectCoupon")
    public List<Coupon> selectCoupon(@RequestParam("token")String token);

}
