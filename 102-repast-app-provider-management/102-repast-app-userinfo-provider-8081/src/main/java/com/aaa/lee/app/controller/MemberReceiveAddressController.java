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
    public ResultData<List<MemberReceiveAddress>> selectAddress(@RequestParam("token") String token){
        //调用service的查询所有地址的方法
        return memberReceiveAddressService.selectAddress(token);
    }

    /**
     * 保存或修改会员收货地址
     * @param memberReceiveAddress
     * @return
     */
    @PostMapping("/saveOrUpdateAddress")
    public Map<String, Object> saveOrUpdateAddress(@RequestBody MemberReceiveAddress memberReceiveAddress,@RequestParam("token") String token){
        //调用service的保存或修改会员地址的方法
        return memberReceiveAddressService.saveOrUpdateAddress(memberReceiveAddress,token);
    }

    /**
     * 根据会员id查询会员收货地址
     * @param
     * @return
     */
    @PostMapping("/findAddressByMemberId")
    public Map<String,Object> findAddressByMemberId(@RequestParam("memberId") Long memberId,@RequestParam("token") String token){
        return memberReceiveAddressService.findAddressByMemberId(memberId,token);
    }

    /**
     * 根据id删除会员收货地址
     * @param id
     * @return
     */
    @PostMapping("/deleteAddress")
    public Map<String,Object> deleteAddress(@RequestParam("id") Long id,@RequestParam("token") String token){
        return memberReceiveAddressService.deleteAddress(id,token);
    }


    /**
     * 根据id修改会员地址的是否为默认地址
     *       0  设置为不默认
     *       1  设置为默认
     * @param id
     * @return
     */
    @PostMapping("/updateAddressStatus")
    public Map<String,Object> updateAddressStatus(@RequestParam("id") Long id,@RequestParam("memberId") Long memberId,@RequestParam("token") String token){
        return memberReceiveAddressService.updateAddressStatus(id,memberId,token);
    }

}
