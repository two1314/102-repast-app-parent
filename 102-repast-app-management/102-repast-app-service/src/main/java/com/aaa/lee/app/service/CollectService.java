package com.aaa.lee.app.service;

import com.aaa.lee.app.base.BaseService;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.mapper.CollectMapper;
import com.aaa.lee.app.mapper.MemberMapper;
import com.aaa.lee.app.mapper.ProductMapper;
import com.aaa.lee.app.model.Collect;
import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.Product;
import com.aaa.lee.app.staticproperties.StaticProperties;
import com.aaa.lee.app.status.StatusEnum;
import com.aaa.lee.app.utils.DateUtil;
import com.aaa.lee.app.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CollectService extends BaseService<Collect> {

    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Mapper<Collect> getMapper() {
        return collectMapper;
    }

    /**
     * 根据会员id查询已收藏的商品
     *
     * @return
     */
    public Map<String, Object> selectCollectMemberId(String token) {
        Map<String, Object> resultMap = new HashMap<>();
        //如果token不为空
        if (!"".equals(token) && token != null) {
            Member member = new Member();
            //查出会员表的token
            Member memberOne = memberMapper.selectOne(member.setToken(token));
            if (null != memberOne.getId() && !"".equals(memberOne.getId())) {
                //查出收藏表的memberId
                Collect collect = collectMapper.selectCollectMemberId(memberOne.getId());
                if (!"".equals(collect.getProductId()) && collect.getProductId() != null) {
                    //根据商品id查询出所有的商品
                    Product product = productMapper.listAll(collect.getProductId());
                    if (!"".equals(product) && product != null) {
                        resultMap.put("code", StatusEnum.SUCCESS.getCode());
                        resultMap.put("msg", StatusEnum.SUCCESS.getMsg());
                        resultMap.put("data", product);
                        return resultMap;
                    } else {
                        resultMap.put("code", StatusEnum.FAILED.getCode());
                        resultMap.put("msg", StatusEnum.FAILED.getMsg());
                    }
                } else {
                    resultMap.put("code", StatusEnum.FAILED.getCode());
                    resultMap.put("msg", StatusEnum.FAILED.getMsg());
                }
            } else {
                resultMap.put("code", StatusEnum.FAILED.getCode());
                resultMap.put("msg", StatusEnum.FAILED.getMsg());
            }
        }
        return resultMap;
    }

    /**
     * 添加收藏的商品
     *
     * @param collect
     * @return
     */
    public Map<String, Object> savesCollect(Collect collect) {
        HashMap<String, Object> resultMap = new HashMap<>();
        if ("".equals(collect.getId()) || null == collect.getId()) {
            //获取当前时间
            /*collect.setCreateTime(new Date());
            collect.setStatus(StaticProperties.COLLECT_STATUS);*/
            int count = collectMapper.savesCollect(collect);
            if (count == 1) {
                resultMap.put("code", StatusEnum.SUCCESS.getCode());
                resultMap.put("msg", StatusEnum.SUCCESS.getMsg());
                resultMap.put("data", count);
            } else {
                resultMap.put("code", StatusEnum.FAILED.getCode());
                resultMap.put("msg", StatusEnum.FAILED.getMsg());
                resultMap.put("data", count);
            }
        }
        return resultMap;
    }

    /**
     * 根据id取消已收藏的商品
     *
     * @return
     */
    public Map<String, Object> updateCollectStatus(Long id) {
        HashMap<String, Object> resultMap = new HashMap<>();
        if (null != id) {
            int count = collectMapper.updateCollectStatus(id);
            if (count != 1) {
                resultMap.put("code", StatusEnum.SUCCESS.getCode());
                resultMap.put("msg", StatusEnum.SUCCESS.getMsg());
                return resultMap;
            }
        }
        return resultMap;
    }
}
