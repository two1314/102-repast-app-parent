package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.MemberReceiveAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "所有地址信息", tags = "所有地址信息接口")
public class MemberReceiveAddressController extends BaseController {

    @Autowired
    private IRepastService repastService;

    /**
     * 查询会员所有的收货地址
     * @return
     */
    @PostMapping("/selectAddress")
    @ApiOperation(value = "查询", notes = "查询所有地址")
    public ResultData selectAddress(String token){
        if (repastService.selectAddress(token) != null){
            //查询成功
            return success();
        }
        return failed();
    }

    /**
     * 保存或修改会员收货地址
     * @param memberReceiveAddress
     * @return
     */
    @PostMapping("/savaOrUpdateAddress")
    @ApiOperation(value = "保存或修改", notes = "保存或修改收货地址")
    public ResultData savaOrUpdateAddress(MemberReceiveAddress memberReceiveAddress){
        Map<String, Object> resultMap = repastService.saveOrUpdateAddress(memberReceiveAddress);
        if (null != resultMap){
            return success();
        }
        return failed();

    }

    /**
     * 根据会员id查询会员收货地址
     * @param memberId
     * @return
     */
    @PostMapping("/findAddressByMemberId")
    @ApiOperation(value = "根据id查询", notes = "根据id查询会员收货地址")
    public ResultData findAddressByMemberId(Long memberId){
        if (null != repastService.findAddressByMemberId(memberId)){
            return success(repastService.findAddressByMemberId(memberId));
        }
        return failed();
    }


    /**
     * 根据会员id删除会员收货地址
     * @param id
     * @return
     */
    @PostMapping("/deleteAddress")
    @ApiOperation(value = "根据id删除", notes = "根据id删除会员收货地址")
    public ResultData deleteAddress(Long id){
        if (null != repastService.deleteAddress(id)){
            return success();
        }
        return failed();
    }



    /**
     * 根据会员id修改会员地址的是否为默认地址
     *       0  设置为不默认
     *       1  设置为默认
     * @param id
     * @return
     */
    @PostMapping("/updateAddressStatus")
    @ApiOperation(value = "修改默认状态", notes = "根据id修改会员地址的是否为默认地址")
    public ResultData updateAddressStatus(Long id){
        if (null != repastService.updateAddressStatus(id)){
            return success(repastService.updateAddressStatus(id));
        }
        return failed();
    }



}