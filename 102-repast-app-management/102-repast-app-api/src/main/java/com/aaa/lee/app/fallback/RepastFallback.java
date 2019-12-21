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
                System.out.println("测试优惠券熔断");
                return null;
            }

            @Override
            public Map<String, Object> selectIntegration(String token) {
                System.out.println("测试会员积分熔断");
                return null;
            }

            @Override
            public Map<String, Object> updateCoupon(Integer useCount, Long id, String token) {
                System.out.println("测试修改会员优惠券");
                return null;
            }

            @Override
            public Member getUser(String token) {
                System.out.println("测试返回用户");
                return null;
            }

            @Override
            public Map<String, Object> selectIntegrationHis(String token) {
                System.out.println("测试用户使用积分记录");
                return null;
            }
        };
    }
}
