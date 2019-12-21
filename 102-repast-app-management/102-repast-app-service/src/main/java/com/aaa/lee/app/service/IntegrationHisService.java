package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.IntegrationHisMapper;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.model.IntegrationHis;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: 102-repast-app-parent
 * @description
 * @author: sjx
 * @create: 2019-12-21 11:09
 **/
@Service
public class IntegrationHisService extends BaseService<IntegrationHis> {

    @Autowired
    private IntegrationHisMapper integrationHisMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public Mapper<IntegrationHis> getMapper() {
        return integrationHisMapper;
    }

    /**
     * 查询当前会员的积分历史
     * @param token
     * @return
     */
    public Map<String,Object> selectIntegrationHis(String token){
        Map<String,Object>map=new HashMap<String, Object>();
        if (null!=token){
            Member member=new Member();
            member.setToken(token);
            Member one = memberMapper.selectOne(member);
            Long memberId = one.getId();
            IntegrationHis integrationHis=new IntegrationHis();
            integrationHis.setMemberId(memberId);
            IntegrationHis integrationHis1 = integrationHisMapper.selectOne(integrationHis);
            if (null!=integrationHis1){
                map.put("code", StatusEnum.EXIST.getCode());
                map.put("msg",StatusEnum.EXIST.getMsg());
                map.put("token",token);
                map.put("data",integrationHis1);
            }else {
                map.put("code", StatusEnum.NOT_EXIST.getCode());
                map.put("msg",StatusEnum.NOT_EXIST.getMsg());
            }
        }else {
            map.put("code",201);
            map.put("msg","token为空");
        }
        return map;
    }
}
