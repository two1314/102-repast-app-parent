package com.aaa.lee.app.controller;

import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.MemberReceiveAddress;
import com.aaa.lee.app.service.MemberReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MemberReceiveAddressController {

    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;

    /**
     * 查询会员所有的收货地址
     * @return
     */
    @PostMapping("/selectAddress")
    public ResultData<List<MemberReceiveAddress>> selectAddress(){
        //调用service的查询所有地址的方法
        return memberReceiveAddressService.selectAddress();
    }

    /**
     * 保存或修改会员收货地址
     * @param memberReceiveAddress
     * @return
     */
    @PostMapping("/saveOrUpdateAddress")
    public Map<String, Object> saveOrUpdateAddress(@RequestBody MemberReceiveAddress memberReceiveAddress){
        //调用service的保存或修改会员地址的方法
        return memberReceiveAddressService.saveOrUpdateAddress(memberReceiveAddress);
    }

    /**
     * 根据会员id查询会员收货地址
     * @param
     * @return
     */
    @PostMapping("/findAddressByMemberId")
    public Map<String,Object> findAddressByMemberId(@RequestParam("memberId") Long memberId){
        return memberReceiveAddressService.findAddressByMemberId(memberId);
    }

    /**
     * 根据id删除会员收货地址
     * @param id
     * @return
     */
    @PostMapping("/deleteAddress")
    public Map<String,Object> deleteAddress(@RequestParam("id") Long id){
        return memberReceiveAddressService.deleteAddress(id);
    }


    /**
     * 根据id修改会员地址的是否为默认地址
     *       0  设置为不默认
     *       1  设置为默认
     * @param id
     * @return
     */
    @PostMapping("/updateAddressStatus")
    public Map<String,Object> updateAddressStatus(@RequestParam("id") Long id){
        return memberReceiveAddressService.updateAddressStatus(id);
    }

}
