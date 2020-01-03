package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.OmsOrder;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OmsOrderMapper extends Mapper<OmsOrder> {
    /**
     * 查询订单中的数据
     * @return
     */
    List<OmsOrder> selectOrder(Long id);

    /**
     * 根据用户id查询出,我的订单中的数据
     * @param id
     * @return
     */
    List<OmsOrder> selectMyOrder(Long id);
}