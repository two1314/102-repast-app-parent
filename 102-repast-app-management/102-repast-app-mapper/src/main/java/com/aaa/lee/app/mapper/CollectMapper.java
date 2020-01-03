package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Collect;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CollectMapper extends Mapper<Collect> {


    /**
     * 根据会员id查询已收藏的商品
     * @param memberId
     * @return
     */
     Collect selectCollectMemberId(Long memberId);


    /**
     * 添加收藏的商品
     * @param collect
     * @return
     */
     int savesCollect(Collect collect);

    /**
     * 根据id取消已收藏的商品
     * @param
     * @return
     */
    int updateCollectStatus(Long id);
}