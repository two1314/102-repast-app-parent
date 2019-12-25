package com.aaa.lee.app.fallback;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.Member;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/12/19 14:57
 * @Description
 **/
@Component
public class RepastFallback implements FallbackFactory<IRepastService> {

    @Override
    public IRepastService create(Throwable throwable) {

        return new IRepastService() {

            @Override
            public Map<String,Object> doLogin(Member member) {
                System.out.println("测试登录熔断数据");
                return null;
            }

            @Override
            public List<Coupon> selectCoupon(String token) {
                return null;
            }

            @Override
            public Map<String, Object> updateCoupon(Integer useCount, Long id, String token) {
                return null;
            }


            @Override
            public Map<String, Object> selectMoneyDetal(String token) {
                System.out.println("测试资金详情数据");
                return null;
            }

            @Override
            public Map<String, Object> selectIntegrationHis(String token) {
                return null;
            }

            @Override
            public Member getUser(String token) {
                return null;
            }

            @Override
            public Map<String, Object> selectIntegration(String token) {
                return null;
            }

            @Override
            public Map<String, Object> selectOrder(String token) {
                System.out.println("我的订单");
                return null;
            }

            @Override
            public Map<String, Object> selectOrderDefatl(String token,String orderSn) {
                System.out.println("订单详情数据");
                return null;
            }
        };
    }
}
