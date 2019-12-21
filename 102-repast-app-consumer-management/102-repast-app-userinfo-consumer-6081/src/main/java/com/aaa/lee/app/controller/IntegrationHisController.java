package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.status.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: 102-repast-app-parent
 * @description
 * @author: sjx
 * @create: 2019-12-21 14:33
 **/
@RestController
@Api(value = "用户积分使用记录", tags = "使用积分接口")
public class IntegrationHisController extends BaseController {
    @Autowired
    private IRepastService repastService;


    /**
     * 查询用户使用积分记录
     * @param token
     * @return
     */
    @PostMapping("/selectIntegrationHis")
    @ApiOperation(value = "积分历史", notes = "查询积分历史操作")
    public ResultData selectIntegrationHis(@RequestParam("token") String token){
        if(repastService.selectIntegrationHis(token)!=null) {
            // 查询成功
            return success(repastService.selectIntegrationHis(token), StatusEnum.SUCCESS.getMsg());
        }
        return failed();
    }

}
