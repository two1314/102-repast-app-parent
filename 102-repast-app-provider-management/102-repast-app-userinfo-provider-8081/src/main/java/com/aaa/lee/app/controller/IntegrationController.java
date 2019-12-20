package com.aaa.lee.app.controller;

import com.aaa.lee.app.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: 102-repast-app-parent
 * @description
 * @author: sjx
 * @create: 2019-12-20 14:56
 **/
@RestController
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    /**
     * 查询用户的积分数
     * @param token
     * @return
     */
    @PostMapping("/selectIntegration")
    public Map<String,Object> selectIntegration(@RequestParam("token")String token) {
        // 调用service
        return integrationService.selectIntegral(token);
    }
}
