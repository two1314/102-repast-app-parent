package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;


import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.mapper.OmsOrderMapper;
import com.aaa.lee.app.mapper.OrderReturnApplyMapper;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.OmsOrder;
import com.aaa.lee.app.model.OrderReturnApply;
import com.aaa.lee.app.status.StatusEnum;
import com.aaa.lee.app.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MoneyDetalService extends BaseService<OmsOrder> {
    @Autowired
    private OmsOrderMapper omsOrderMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderReturnApplyMapper orderReturnApplyMapper;
    @Override
    public Mapper<OmsOrder> getMapper() {
        return omsOrderMapper;
    }

    /**
     * 查询资金明细数据
     * @param token
     * @return
     */
  public Map<String,Object> selectOrder(String token)  {
      Map<String, Object> resultMap = new HashMap<>();
      //根据传过来的token,查询用户id
      Member member = new Member();
      member.setToken(token);
      Member member1 = memberMapper.selectOne(member);
     //System.out.println(member1);
      //结合查询出来的用户id查询订单中的数据
      List<OmsOrder> omsOrders = omsOrderMapper.selectOrder(Long.valueOf(member1.getId()));
      if (omsOrders.size()>0){
          //说明查出数据了
          for (OmsOrder omsOrder : omsOrders) {
              Long id = omsOrder.getId();
              OrderReturnApply orderReturnApply = orderReturnApplyMapper.selectById(id);
              if (null!=orderReturnApply && !"".equals(orderReturnApply)){
                  //查出数据了
                  resultMap.put("data",omsOrders);
                  resultMap.put("code",StatusEnum.SUCCESS.getCode());
                  resultMap.put("msg",StatusEnum.SUCCESS.getMsg());
                  resultMap.put("resultMap",orderReturnApply);
              }else{
                  resultMap.put("code",StatusEnum.FAILED.getCode());
                  resultMap.put("msg",StatusEnum.FAILED.getMsg());
              }
              resultMap.put("data",omsOrders);
              resultMap.put("code",StatusEnum.SUCCESS.getCode());
              resultMap.put("msg",StatusEnum.SUCCESS.getMsg());
          }
      }else{
         return null;
      }
      return resultMap;
  }
}
