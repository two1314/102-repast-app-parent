package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Coupon;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author sjx
 */
@Repository
public interface CouponMapper extends Mapper<Coupon> {

    /**
     * 通过token查询会员拥有的优惠券
     * @param token
     * @return
     */
    public List<Coupon>selectByToken(String token);
}