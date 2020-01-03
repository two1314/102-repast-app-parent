package com.aaa.lee.app.fallback;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.*;
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

            @Override
            public void addLoginLog(LoginLog loginLog) {

            }

            @Override
            public ResultData<List<MemberReceiveAddress>> selectAddress(String token) {
                System.out.println("测试地址查询熔断数据");
                return null;
            }

            @Override
            public Map<String, Object> saveOrUpdateAddress(MemberReceiveAddress memberReceiveAddress) {
                System.out.println("测试保存或修改会员收货地址熔断数据");
                return null;
            }

            @Override
            public Map<String, Object> findAddressByMemberId(Long memberId) {
                System.out.println("测试根据会员id查询收货地址熔断数据");
                return null;
            }

            @Override
            public Map<String, Object> deleteAddress(Long id) {
                System.out.println("测试根据id删除收货地址熔断数据");
                return null;
            }

            @Override
            public Map<String, Object> updateAddressStatus(Long id) {
                System.out.println("测试根据id会员地址的是否为默认地址熔断数据");
                return null;
            }

            @Override
            public Map<String, Object> selectCollectMemberId(String token) {
                return null;
            }

            @Override
            public Map<String, Object> savesCollect(Collect collect) {
                return null;
            }

            @Override
            public Map<String, Object> updateCollectStatus(Long id) {
                return null;
            }
        };
    }
}
