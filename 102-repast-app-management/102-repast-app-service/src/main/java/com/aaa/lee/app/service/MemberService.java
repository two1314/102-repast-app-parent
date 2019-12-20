package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.utils.IDUtil;
import com.aaa.lee.app.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/12/19 14:00
 * @Description
 **/
@Service
public class MemberService extends BaseService<Member> {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Mapper<Member> getMapper() {
        return memberMapper;
    }

    /**
     * @param
     * @return java.lang.Boolean
     * @throws
     * @author Seven Lee
     * @description 执行登录操作
     * @date 2019/12/19
     **/
    public String doLogin(Member member) {
        // 随机token
        String token = IDUtil.getUUID() + member.getOpenId();


        if (StringUtil.isNotEmpty(member.getOpenId())) {
            Member one = memberMapper.selectOne(member);
            member.setToken(token);
            if (one!=null) {
                Integer saveResult = memberMapper.updateByOpenId(member);
                if (saveResult>0){
                    return token;
                }
            } else {
                try {
//                member.setToken(token);
                    Integer saveResult = super.save(member);
                    if (saveResult > 0) {
                        // 说明添加成功
                        return token;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return null;
    }


}
