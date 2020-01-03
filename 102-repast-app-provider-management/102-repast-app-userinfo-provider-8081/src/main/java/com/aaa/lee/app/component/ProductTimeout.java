package com.aaa.lee.app.component;

import com.aaa.lee.app.service.CollectService;
import com.aaa.lee.app.status.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableScheduling
public class ProductTimeout {

    @Autowired
    private CollectService collectService;

    /**
     * 定时检查将下架的商品移除
     * 每天凌晨一点开始检查
     */
    @Scheduled(cron = "0 00 01 * * ?")
    private void ProductTimeout(){
        System.out.println("定时检查");
        Map<String, Object> resultMap = collectService.deleteProductDrop(StatusEnum.SUCCESS.getCode());
        if (resultMap.size() > 0){
            System.out.println("该商品已下架");
        }
    }
}
