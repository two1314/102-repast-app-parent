package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.mapper.OmsOrderItemMapper;
import com.aaa.lee.app.mapper.OmsOrderMapper;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.OmsOrder;
import com.aaa.lee.app.model.OmsOrderItem;
import com.aaa.lee.app.staticproperties.StaticProperties;
import com.aaa.lee.app.status.StatusEnum;
import com.aaa.lee.app.utils.DateUtil;
import com.aaa.lee.app.utils.JSONUtil;
import com.aaa.lee.app.vo.MyOrderVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService extends BaseService<OmsOrder> {

    @Autowired
    private OmsOrderMapper omsOrderMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;

    @Override
    public Mapper<OmsOrder> getMapper() {
        return omsOrderMapper;
    }

    /**
     * 查询我的订单Test 测试vo
     * @param token
     * @return
     */
    public Map<String,Object> selectTestOrder(String token){
        Map<String, Object> resultMap = new HashMap<>();
        //根据传过来的token,查询用户id
        Member member = new Member();
        member.setToken(token);
        Member member1 = memberMapper.selectOne(member);
        List<MyOrderVo> myOrderVos = memberMapper.selectId(member1.getId());
        resultMap.put("data",myOrderVos);
        return resultMap;
    }
    /**
     * 查询我的订单中
     * @param token
     * @return
     */
    public Map<String,Object> selectOrder(String token){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            //根据token去远程调用订单组的
            // 下单后的数据
            resultMap.put(StaticProperties.TOKEN,token);
            //restTemplate.getForObject("调用的地址",resultMap,String.class);

            String result = restTemplate.postForObject("http://192.168.1.40:6081/consumer/selectMyOrder", resultMap, String.class);
            if (!"".equals(result)){
                Map map = JSON.parseObject(result);
                List<OmsOrder> omsOrder = JSONUtil.toObject(map.get("data").toString(), List.class);
                resultMap.put("data",omsOrder);
            }
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("服务器正在忙");
            return null;
        }

    }

    /**
     * 查看订单详情的数据
     * @param token
     * @return
     */
    public Map<String,Object> selectOrderDefatl(String token,String orderSn) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //根据token去远程调用订单组的
            // 下单后的数据
            resultMap.put(StaticProperties.TOKEN, token);
            resultMap.put(StaticProperties.ORDERSN, orderSn);
            String result = restTemplate.postForObject("http://192.168.1.40:6081/consumer/selectMyOrderById", resultMap, String.class);
            // JSONUtil.toObject(result, OmsOrderItem.class);
            resultMap.put("result", result);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务器正在忙!");
        }
        return null;
    }


}
