package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.status.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: 102-repast-app-parent
 * @description
 * @author: sjx
 * @create: 2019-12-20 15:09
 **/
@RestController
@Api(value = "用户积分", tags = "用户积分接口")
public class IntegrationController extends BaseController {

    @Autowired
    private IRepastService repastService;

    /**
     *
     * @param token
     * @return
     */
    @PostMapping("/selectIntegration")
    @ApiOperation(value = "用户积分", notes = "查询用户积分操作")
    public ResultData selectIntegration(String token) {
        if (null!=token){
            if(repastService.selectIntegration(token)!=null) {
                // 查询成功
                return success(repastService.selectIntegration(token), StatusEnum.SUCCESS.getMsg());
            }
        }


        return failed(StatusEnum.FAILED.getMsg());
    }

}
