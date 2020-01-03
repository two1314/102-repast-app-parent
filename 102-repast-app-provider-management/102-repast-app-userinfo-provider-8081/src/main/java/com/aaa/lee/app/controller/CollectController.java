package com.aaa.lee.app.controller;

import com.aaa.lee.app.model.Collect;
import com.aaa.lee.app.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 根据会员id查询已收藏的商品
     * @return
     */
    @PostMapping("/selectCollectMemberId")
    public Map<String,Object> selectCollectMemberId(@RequestParam("token") String token){
        return collectService.selectCollectMemberId(token);
    }


    /**
     * 添加收藏的商品
     * @param collect
     * @return
     */
    @PostMapping("/savesCollect")
    public Map<String,Object> savesCollect(@RequestBody Collect collect){
        return collectService.savesCollect(collect);
    }


    /**
     * 根据id取消已收藏的商品
     * @return
     */
    @PostMapping("/updateCollectStatus")
    public Map<String,Object> updateCollectStatus(@RequestParam("id") Long id){

        return collectService.updateCollectStatus(id);
    }
}
