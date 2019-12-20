package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author sjx
 */
@Repository
public interface OrderMapper extends Mapper<Order> {


    /**
     * 查询用户的下单数
     * @param token
     * @return
     */
    public Integer count(String token);
}