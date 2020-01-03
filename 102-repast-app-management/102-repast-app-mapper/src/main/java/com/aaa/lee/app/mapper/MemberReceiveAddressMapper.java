package com.aaa.lee.app.mapper;

import com.aaa.lee.app.model.MemberReceiveAddress;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository

public interface MemberReceiveAddressMapper extends Mapper<MemberReceiveAddress> {


    int deleteAddress(Long id);

    /**
     * 根据memberId修改会员地址的全部为不默认地址
     * @param memberId
     * @return
     */

    int updateAllAddressStatus(Long memberId);

    /**
     * 根据id修改会员地址的是否为默认地址
     *       0  设置为不默认
     *       1  设置为默认
     * @param id
     * @return
     */
    int updateAddressStatus(Long id);
}