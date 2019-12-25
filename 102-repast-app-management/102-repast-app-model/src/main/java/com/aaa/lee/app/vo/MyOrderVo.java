package com.aaa.lee.app.vo;

import com.aaa.lee.app.model.Member;
import com.aaa.lee.app.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MyOrderVo extends Member {


    /**
     * 查询我的订单
     */
    private List<Order> orders;

}
