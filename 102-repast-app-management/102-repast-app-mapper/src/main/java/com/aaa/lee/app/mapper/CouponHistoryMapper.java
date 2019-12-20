package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.CouponHistory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author sjx
 */
@Repository
public interface CouponHistoryMapper extends Mapper<CouponHistory> {

    /**
     * 通过用户id和订单id修改优惠券
     * @param couponHistory
     * @return
     */
    public Integer  updateByMemberIdAndCouponId(CouponHistory couponHistory);
}