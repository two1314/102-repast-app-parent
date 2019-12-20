package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.CouponHistoryMapper;
import com.aaa.lee.app.mapper.CouponMapper;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.CouponHistory;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CouponHistoryMapper couponHistoryMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public Mapper<Coupon> getMapper() {
        return couponMapper;
    }

    /**
     * 查询该用户拥有的优惠券
     * @return
     */
   public List<Coupon> selectCoupon(String token){
       if (token!=null){
           List<Coupon> list = couponMapper.selectByToken(token);
           if (list.size()>0){
                return list;
           }
       }
       return null;
   }


    /**
     * 用户使用过优惠券对优惠券的修改
     * @param useCount
     * @param
     * @param token
     * @return
     */
   public Map<String,Object> updateCoupon(Integer useCount, Long couponId, String token){
       Map<String,Object>map=new HashMap<String, Object>();
       if (null!=token){
            Coupon coupon=new Coupon();
            coupon.setId(couponId);
            coupon.setUseCount(useCount);
            try {
                Member member=new Member();
                member.setToken(token);
                Member one = memberMapper.selectOne(member);
                Long memberId = one.getId();
                Integer updateResult = super.update(coupon);
                CouponHistory couponHistory=new CouponHistory();
                couponHistory.setMemberId(memberId);
                couponHistory.setCouponId(couponId);
                couponHistoryMapper.updateByMemberIdAndCouponId(couponHistory);
                if (updateResult>0){
                    map.put("code",StatusEnum.SUCCESS.getCode());
                    map.put("msg",StatusEnum.SUCCESS.getMsg());
                    map.put("data",updateResult);
                    map.put("token",token);
                }else {
                    map.put("code",StatusEnum.FAILED.getCode());
                    map.put("msg",StatusEnum.FAILED.getMsg());
                    map.put("token",token);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       return map;
   }
}
