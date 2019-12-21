package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Coupon;
import com.aaa.lee.app.model.Member;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/12/19 14:53
 * @Description
 **/
@RestController
@Api(value = "用户信息", tags = "用户信息接口")
public class MemberController extends BaseController {

    @Autowired
    private IRepastService repastService;

    /**
     * @author Seven Lee
     * @description
     *      执行登录操作
     * @param
     * @date 2019/12/19
     * @return com.aaa.lee.app.base.ResultData
     * @throws 
    **/
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录", notes = "执行登录操作")
    public ResultData doLogin(Member member) {
        if(repastService.doLogin(member)!=null) {
            // 登录成功
            return success(repastService.doLogin(member));
        }
        return failed();
    }


    /**
     * 通过传递的token返回用户对象
     * @param
     * @return
     */
    @PostMapping("/getUser")
    public String getUser(@RequestBody Map<String, Object> map) {
        if (map.isEmpty()){
            return "";
        }
        String token = null == map.get("token") ? "" : map.get("token").toString();
        Member user = repastService.getUser(token);
        String data ;
        if (null!=user){
             data = JSON.toJSON(user).toString();
             return data;
        }
        return null;
    }


}
