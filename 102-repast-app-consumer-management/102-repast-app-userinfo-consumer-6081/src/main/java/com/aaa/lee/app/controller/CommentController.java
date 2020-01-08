package com.aaa.lee.app.controller;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.base.BaseController;
import com.aaa.lee.app.base.ResultData;
import com.aaa.lee.app.model.Comment;
import com.aaa.lee.app.vo.FileCommentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(value = "评价信息",tags = "评价信息的接口")
public class CommentController extends BaseController {
    @Autowired
    private IRepastService repastService;

    /**
     * 查看个人评价
     * @param memberId
     * @return
     */
    @PostMapping("/selectComment")
    @ApiOperation(value = "查看个人评价", notes = "查看个人评价")
    public ResultData selectAll(Long memberId){
        return repastService.selectAll(memberId);
    }

    /**
     * 查看商品评价
     * @param productId
     * @return
     */
    @PostMapping("/listAllComment")
    @ApiOperation(value = "查看商品评价", notes = "查看商品评价")
    public ResultData listAllComment(Long productId){
        return repastService.listAllComment(productId);
    }

    /**
     * 添加评价
     * @param comment
     * @param token
     * @param file
     * @return
     */
    @PostMapping("/insertComment")
    @ApiOperation(value = "添加评价", notes = "添加评价")
    public ResultData insertComment(Comment comment,String token,MultipartFile file){
        FileCommentVo fileCommentVo = new FileCommentVo();
        fileCommentVo.setComment(comment).setToken(token);

        return repastService.insertComment(fileCommentVo);
    }

    /**
     * 上传图片方法
     * @param file
     * @return
     */
    @PostMapping(value = "/upPicture",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传图片")
    public ResultData upPicture(@RequestPart(value = "file") MultipartFile file){
        return null;
    }

}
