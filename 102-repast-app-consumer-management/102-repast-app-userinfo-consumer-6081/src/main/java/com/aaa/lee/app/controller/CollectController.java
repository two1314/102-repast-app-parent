package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Collect;
import com.aaa.lee.app.status.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@Api(value = "收藏信息", tags = "所有收藏信息接口")
public class CollectController extends BaseController {

    @Autowired
    private IRepastService repastService;

    /**
     * 根据会员id查询已收藏的商品
     * @param token
     * @return
     */
    @PostMapping("/selectCollectMemberId")
    @ApiOperation(value = "根据会员id查询", notes = "根据会员id查询已收藏的商品")
    public ResultData selectCollectMemberId(String token){
        if (repastService.selectAddress(token) != null){
            Map<String, Object> map = repastService.selectCollectMemberId(token);
            //查询成功
            return success(map,StatusEnum.SUCCESS.getMsg());
        }
        return failed();
    }

    /**
     * 添加收藏的商品
     * @param collect
     * @return
     */
    @PostMapping("/savesCollect")
    @ApiOperation(value = "添加", notes = "添加收藏的商品")
    public ResultData savesCollect(Collect collect,String token){
        if (null != repastService.savesCollect(collect,token)){
            return success(repastService.savesCollect(collect,token), StatusEnum.SUCCESS.getMsg());
        }
        return failed();
    }

    /**
     * 根据id取消已收藏的商品
     * @param id
     * @return
     */
    @PostMapping("/updateCollectStatus")
    @ApiOperation(value = "根据id取消", notes = "根据id取消已收藏的商品")
    public ResultData updateCollectStatus(Long id,String token){
        if (null != repastService.updateCollectStatus(id,token)){
            return success(repastService.updateCollectStatus(id,token),StatusEnum.SUCCESS.getMsg());
        }
        return failed();
    }

    /**
     * 查询出下架的商品 然后再将其移除收藏表
     * @param token
     * @return
     */
    @PostMapping("/deleteProductDrop")
    @ApiOperation(value = "移除下架商品", notes = "查询出下架的商品 然后再将其移除收藏表")
    public ResultData deleteProductDrop(String token){
        if (null != repastService.deleteProductDrop(token)){
            return success(StatusEnum.SUCCESS.getMsg());
        }
        return failed();
    }


}
