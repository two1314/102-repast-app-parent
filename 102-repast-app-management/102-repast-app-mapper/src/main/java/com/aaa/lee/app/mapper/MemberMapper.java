package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Member;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author sjx
 */
public interface MemberMapper extends Mapper<Member> {


    /**
     * 通过openid修改token值
     * @param member
     * @return
     */
    Integer updateByOpenId(Member member);
}