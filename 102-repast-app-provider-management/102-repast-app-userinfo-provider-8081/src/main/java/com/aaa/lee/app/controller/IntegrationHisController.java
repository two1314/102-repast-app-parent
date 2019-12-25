package com.aaa.lee.app.controller;

import com.aaa.lee.app.service.IntegrationHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: 102-repast-app-parent
 * @description
 * @author: sjx
 * @create: 2019-12-21 14:28
 **/
@RestController
public class IntegrationHisController {
    @Autowired
    private IntegrationHisService integrationHisService;

    /**
     * 查询用户使用积分记录
     * @param token
     * @return
     */
    @PostMapping("/selectIntegrationHis")
    public Map<String,Object>selectIntegrationHis(@RequestParam("token") String token){

        return integrationHisService.selectIntegrationHis(token);
    }
}