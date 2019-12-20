package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.IntegrationHis;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author sjx
 */
@Repository
public interface IntegrationHisMapper extends Mapper<IntegrationHis> {

    /**
     * 查询会员消费的积分
     * @param id
     * @return
     */
    public List<IntegrationHis>selectIntegrationHis(Long id);
}