package com.aaa.lee.app.controller;

import com.aaa.lee.app.service.MoneyDetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MoneyDetalController{
@Autowired
private MoneyDetalService moneyDetalService;
    /**
     * 查询资金明细所有数据
     * @param token
     * @return
     */
    @PostMapping("/selectMoneyDetal")
   public Map<String,Object> selectMoneyDetal(@RequestParam("token") String token){
          return moneyDetalService.selectOrder(token);
    }
}
