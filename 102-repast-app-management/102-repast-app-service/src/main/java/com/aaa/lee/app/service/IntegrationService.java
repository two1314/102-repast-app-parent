package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.IntegrationHisMapper;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.mapper.OrderMapper;
import com.aaa.lee.app.model.IntegrationHis;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.Order;
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
 * @create: 2019-12-20 14:18
 **/
@Service
public class IntegrationService extends BaseService<Order> {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IntegrationHisMapper integrationHisMapper;
    @Autowired
     private MemberMapper memberMapper;
    @Override
    public Mapper<Order> getMapper() {
        return orderMapper;
    }

    /**
     * 查询会员的积分
     * @param token
     * @return
     */
    public Map<String,Object>selectIntegral(String token){
        Map<String,Object>map=new HashMap<String, Object>();
        Integer integral;
        if (null!=token){
            //查询总共的下单数
            Integer count = orderMapper.count(token);
            //总积分 每单50积分
            integral=count*50;
            Member member=new Member();
            member.setToken(token);
            Member one = memberMapper.selectOne(member);
            if (one!=null){
                map.put("code",200);
                map.put("msg","成功");
                map.put("data",one);
            }else {
                map.put("code",500);
                map.put("msg","失败");
            }
            Long id = one.getId();
            Integer i=0;
            List<IntegrationHis> integrationHis = integrationHisMapper.selectIntegrationHis(id);
            for (IntegrationHis integrationHi : integrationHis) {
                Integer integration = integrationHi.getChangeCount();
                i=i+integration;
            }
            i=integral-i;
            map.put("integral",integral);
            map.put("i",i);

        }
        return map;
    }

}
