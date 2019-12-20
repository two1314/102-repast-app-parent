package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.CouponMapper;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @program: 102-repast-app-parent
 * @description
 * @author: sjx
 * @create: 2019-12-19 20:50
 **/
@Service
public class CouponService extends BaseService<Coupon> {

    @Autowired
    private CouponMapper couponMapper;
    @Override
    public Mapper<Coupon> getMapper() {
        return couponMapper;
    }

    /**
     * 查询该用户拥有的优惠券
     * @return
     */
   public List<Coupon>selectCoupon(String token){
       if (token!=null){
           List<Coupon> list = couponMapper.selectByToken(token);
           if (list.size()>0){
                return list;
           }
       }
       return null;
   }
}
