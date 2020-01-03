package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.Collect;
import com.aaa.lee.app.model.Product;
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

    /**
     * 查询出下架的商品
     */
    List<Collect> selectProductDrop();


    /**
     * 根据收藏表中的id（下架商品）将下架的商品移除
     */
    int updateCollectDrop(Long id);

}