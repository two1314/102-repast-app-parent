package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.utils.IDUtil;
import com.aaa.lee.app.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> doLogin(Member member) {
        Map<String,Object>map=new HashMap<String, Object>();
        // 随机token
        String token = IDUtil.getUUID() + member.getOpenId();
        if (StringUtil.isNotEmpty(member.getOpenId())) {
            Member one = memberMapper.selectOne(member);
            member.setToken(token);
            if (one!=null) {
                Integer saveResult = memberMapper.updateByOpenId(member);
                if (saveResult>0){
                    Member m=new Member();
                    m.setToken(token);
                    Member member1 = memberMapper.selectOne(m);
                    map.put("token",token);
                    map.put("data",member1);
                    return map;
                }
            } else {
                try {
                    Integer result = super.save(member);
                    if (result > 0) {
                        // 说明添加成功
                        Member m=new Member();
                        m.setToken(token);
                        Member m1 = memberMapper.selectOne(m);
                        map.put("token",token);
                        map.put("data",m1);

                        return map;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return null;
    }


    /**
     * 通过token返回用户对象
     * @param token
     * @return
     */
    public Member getUser(String token){
        Member member1 = new Member();
        member1.setToken(token);
        Member member = memberMapper.selectOne(member1);
        if (null==member){
            return null;
        }
        return member;
    }

}
