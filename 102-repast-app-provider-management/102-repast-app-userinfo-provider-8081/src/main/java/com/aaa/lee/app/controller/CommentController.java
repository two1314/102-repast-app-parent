package com.aaa.lee.app.controller;

import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Comment;
import com.aaa.lee.app.service.CommentService;
import com.aaa.lee.app.status.StatusEnum;
import com.aaa.lee.app.vo.FileCommentVo;
import com.aaa.lee.app.vo.MyCommentVo;
import com.aaa.lee.app.vo.ProductAllCollectVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;

    /**
     * 查询我的评价
     * @param memberId
     * @return
     */
    @PostMapping("/selectComment")
    public ResultData selectAll(@RequestParam("memberId") Long memberId){
        List<MyCommentVo> myCommentVos = commentService.listMyComment(memberId);
        if (myCommentVos.size()>0){
            return super.success(myCommentVos,StatusEnum.EXIST.getMsg());
        }else {
            return super.failed(StatusEnum.NOT_EXIST.getMsg());
        }
    }
    /**
     * 查看商品评价
     * @param productId
     * @param
     * @return
     */
    @PostMapping("/listAllComment")
    public ResultData listAllComment(@RequestParam("productId") Long productId){
        List<ProductAllCollectVo> productAll = commentService.listAllComment(productId);
        if (!"".equals(productAll) && null != productAll){
            return super.success(productAll,StatusEnum.EXIST.getMsg());
        }else {
            return super.failed(StatusEnum.NOT_EXIST.getMsg());
        }
    }

    /**
     * 添加评价
     * @return
     */
    @PostMapping("/insertComment")
    public ResultData insertComment(@RequestBody FileCommentVo fileCommentVo){
        ResultData data = commentService.insertComment(fileCommentVo);
        if (!"".equals(data) && null!=data){
            return super.success(data,StatusEnum.EXIST.getMsg());
        }else {
            return super.failed(StatusEnum.NOT_EXIST.getMsg());
        }

    }


    /**
     * 图片上传
     * @param file
     * @return
     */
    @PostMapping(value = "/upPicture",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultData upPicture(@RequestParam("file") MultipartFile file){
        String upPicture = commentService.upPicture(file);
        if (null!=upPicture){
            return super.success(upPicture,StatusEnum.EXIST.getMsg());
        }else {
            return super.failed(StatusEnum.NOT_EXIST.getMsg());
        }
    }


}
