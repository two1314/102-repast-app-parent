package com.aaa.lee.app.api;

import com.aaa.lee.app.fallback.RepastFallback;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
    Map<String,Object> doLogin(@RequestBody Member member);

    /**
     * 通过token查询会员熔断
     * @param token
     * @return
     */
    @PostMapping("/selectCoupon")
    public List<Coupon> selectCoupon(@RequestParam("token")String token);

    /**
     *修改优惠用户的优惠券
     * @param useCount
     * @param id
     * @param token
     * @return
     */
    @PostMapping("/updateCoupon")
    public Map<String, Object> updateCoupon(@RequestParam("useCount")Integer useCount, @RequestParam("id") Long id, @RequestParam("token")String token);
    /**
     *
     * 查询资金明细所有数据
     * @param token
     * @return
     */
    @PostMapping("/selectMoneyDetal")
    public Map<String,Object> selectMoneyDetal(@RequestParam("token") String token);

    /**
     * 查询用户使用积分记录
     * @param token
     * @return
     */
    @PostMapping("/selectIntegrationHis")
    public Map<String,Object>selectIntegrationHis(@RequestParam("token") String token);

    /**
     * 查询用户的积分数
     * @param token
     * @return
     */
    /**
     * 通过token返回用户对象
     * @param token
     * @return
     */
    @PostMapping("/getUser")
    Member getUser(@RequestParam("token") String token);
    /**
     * 查询用户的积分数
     * @param token
     * @return
     */
    @PostMapping("/selectIntegration")
    public Map<String,Object> selectIntegration(@RequestParam("token")String token);
    /**
     * 查询我的订单
     * @param token
     * @return
     */
    @PostMapping("/selectOrder")
    public Map<String, Object> selectOrder(@RequestParam("token")String token);

    /**
     * 查看订单详情
     * @param token
     * @param orderSn
     * @return
     */
    @PostMapping("/selectOrderDefatl")
    public Map<String ,Object> selectOrderDefatl(@RequestParam("token")String token,@RequestParam("orderSn")String orderSn);



}
