package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.OrderReturnApply;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderReturnApplyMapper extends Mapper<OrderReturnApply> {
    /**
     * 根据订单id查询退货的
     * @param id
     * @return
     */
    OrderReturnApply selectById(Long id);
}