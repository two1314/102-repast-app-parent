package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.OmsOrder;
import com.aaa.lee.app.vo.MyOrderVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author sjx
 */
@Repository
public interface MemberMapper extends Mapper<Member> {


    /**
     * 通过openid修改token值
     * @param member
     * @return
     */
    Integer updateByOpenId(Member member);

    /**
     * 根据用户id查询用户的订单
     * @param id
     * @return
     */
    List<MyOrderVo> selectId(Long id);
}